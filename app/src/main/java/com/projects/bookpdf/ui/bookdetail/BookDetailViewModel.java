package com.projects.bookpdf.ui.bookdetail;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.projects.bookpdf.activity.MainActivity;
import com.projects.bookpdf.data.ObjectCollection;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

class BookDetailViewModel extends ViewModel implements Observer {
    private Context context;
    private MutableLiveData<Integer> loadRemainingData;

    BookDetailViewModel(Context context) {
        super();
        this.context = context;
        loadRemainingData = new MutableLiveData<>();
        loadRemainingData.setValue(0);
        ObjectCollection.bookDetailNotifier.addObserver(BookDetailViewModel.this);
    }

    //TODO: Download the book!!
    void downloadBook(String downloadUrl, String bookName) {
        Log.e("Download url f method", downloadUrl);
        if (downloadUrl.length() > 0) {
            Log.e("AJM", "calling download task");
            new DownloadTask().execute(downloadUrl, bookName);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        MainActivity.stopProgressDialog();
        if (o instanceof ObjectCollection.BookDetailNotifier)
            loadRemainingData.setValue(loadRemainingData.getValue() + 1);
    }

    MutableLiveData<Integer> getLoadRemainingData() {
        return loadRemainingData;
    }

    class DownloadTask extends AsyncTask<String, Void, Void> {
        int downloadSuccess=0;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            MainActivity.showProgressDialog();
        }

        @Override
        protected Void doInBackground(String... strings) {
            try {
                Log.e("DownloadTaskBackground", "file and path : Inside do in bg");
                String path = Environment.getExternalStorageDirectory().toString()+ "/BookPDF";
                File file = new File(path);
                if (!file.exists()) {
                    file.mkdirs();
                }
                Log.e("DownloadTaskBackground", "file created!");
                HttpURLConnection connection;
                URL url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.setInstanceFollowRedirects(true);
                HttpURLConnection.setFollowRedirects(true);
                connection.setRequestMethod("GET");
                Log.e("DownloadTaskBackground", "connecting to url : "+strings[0]);
                connection.connect();
                Log.e("DownloadTaskBackground", "bellow connection.connect : responseCode() : "+connection.getResponseCode());
                boolean redirect=false;
                if(connection.getResponseCode()!=HttpURLConnection.HTTP_OK&&(connection.getResponseCode()==HttpURLConnection.HTTP_MOVED_PERM
                        ||connection.getResponseCode()==HttpURLConnection.HTTP_MOVED_TEMP
                        ||connection.getResponseCode()==HttpURLConnection.HTTP_SEE_OTHER))
                    redirect = true;

                if(redirect)
                {
                    String newUrl=connection.getHeaderField("Location");
                    String[] splitUrl=newUrl.split(" ");
                    url=new URL(splitUrl[0]);
                    connection= (HttpURLConnection) url.openConnection();
                    connection.connect();
                    Log.e("DownloadTaskBackground", "redirect = true, new url :"+ Arrays.toString(splitUrl) +"\n : responseCode() : "+connection.getResponseCode());
                    String bName="";
                    for(int i=1;i<splitUrl.length;i++)
                    {
                        if(i==splitUrl.length-1)
                            bName += splitUrl[i];
                        else
                            bName += splitUrl[i]+" ";
                    }
                    if(connection.getResponseCode()==200)
                    {
                        Log.e("DownloadTaskBackground","above saveFile() of redirect : "+bName);
                        saveFile(file,bName,connection);
                        return null;
                    }
                    else
                    {
                        return null;
                    }
                }
                else if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    saveFile(file,strings[1],connection);
                    return null;
                } else {
                    Log.e("DownloadTaskBackground","inside else block :");
                    if(connection.getResponseCode()==500)
                    {

                    }
                    //TODO: Undesired response. Show a dialog to the user with appropriate message
                    return null;
                }
            } catch (Exception e) {
                Log.e("DownloadTaskBackground", "Exception  :" + e.getMessage());
                Log.e("DownloadTaskBackground", "Exception  :" + Arrays.toString(e.getStackTrace()));
                return null;
            }
        }
        private void saveFile(File file,String bookName,HttpURLConnection connection) throws IOException {
            InputStream input;
            File savingFile = new File(file, bookName + ".pdf");
            int fileNo = 1;
            Log.e("DownloadTaskBackground","does file exist : "+savingFile.exists());
            while (savingFile.exists()) {
                savingFile = new File(file, bookName + "(" + fileNo + ").pdf");
                fileNo++;
            }
            if(!savingFile.exists())
                savingFile.createNewFile();
            Log.e("DownloadTaskBackground","does file exist : "+savingFile.exists());
            Log.e("DownloadTaskBackground", "file and path : " + savingFile.getName() + "\n" + savingFile.getAbsolutePath());
            FileOutputStream fos = new FileOutputStream(savingFile);
            input = connection.getInputStream();
            byte[] buffer = new byte[1024*1024];
            int len1;
            while ((len1 = input.read(buffer)) != -1) {
                fos.write(buffer, 0, len1);
            }
            fos.close();
            input.close();
            downloadSuccess=1;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            MainActivity.stopProgressDialog();
            if(downloadSuccess==1)
            {
                MainActivity.showSuccessDialog("Book Downloaded");
            }
            else if(downloadSuccess==0)
            {
                MainActivity.showFailureDialog("Sorry, could not Download the Book");
            }
        }
    }
}