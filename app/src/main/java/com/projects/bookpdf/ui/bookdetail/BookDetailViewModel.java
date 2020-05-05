package com.projects.bookpdf.ui.bookdetail;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.projects.bookpdf.activity.MainActivity;
import com.projects.bookpdf.data.ObjectCollection;
import com.projects.bookpdf.database.DBHelper;

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
    void downloadBook(String downloadUrl, String bookName,String bookImgUrl) {
        Log.e("Download url f method", downloadUrl);
        if (downloadUrl.length() > 0) {
            Log.e("AJM", "calling download task");
            new DownloadTask(bookImgUrl,bookName).execute(downloadUrl, bookName);
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
        String bookImgUrl,bookName;

        public DownloadTask(String bookImgUrl, String bookName) {
            this.bookImgUrl = bookImgUrl;
            this.bookName = bookName;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            MainActivity.showProgressDialog();
        }

        @Override
        protected Void doInBackground(String... strings) {
            try {
                String path;
                path = context.getFilesDir().getPath()+ "/BookPDF";
                File file = new File(path);
                if (!file.exists()) {
                    file.mkdirs();
                }
                Log.e("inBG","file :"+file.getPath());
                HttpURLConnection connection;
                URL url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.setInstanceFollowRedirects(true);
                HttpURLConnection.setFollowRedirects(true);
                connection.setRequestMethod("GET");
                connection.connect();
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
            bookName=bookName.replace(" ","_");
            InputStream input;
            File savingFile = new File(file, bookName + ".pdf");
            int fileNo = 1;
            while (savingFile.exists()) {
                savingFile = new File(file, bookName + "(" + fileNo + ").pdf");
                fileNo++;
            }
            if(!savingFile.exists())
                savingFile.createNewFile();
            savingFile.setReadable(true);
            savingFile.setWritable(true);
            savingFile.setExecutable(true);
            FileOutputStream fos = new FileOutputStream(savingFile);
            input = connection.getInputStream();
            byte[] buffer = new byte[1024];
            int len1;
            while ((len1 = input.read(buffer)) != -1) {
                fos.write(buffer, 0, len1);
            }
            fos.close();
            input.close();
            downloadSuccess=1;
            DBHelper dbHelper=new DBHelper(context);
            dbHelper.openDBForWrite();
            dbHelper.insertIntoTblDownloadedBooks(savingFile.getName(),bookImgUrl);
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            MainActivity.stopProgressDialog();
            if(downloadSuccess==1)
            {
                MainActivity.showSuccessDialog(bookName+"\nDownloaded");
            }
            else if(downloadSuccess==0)
            {
                MainActivity.showFailureDialog("Sorry, could not Download\n"+bookName);
            }
        }
    }
}