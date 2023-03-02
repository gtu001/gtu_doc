package gtu.jpg;

import java.io.File;

import gtu.binary.Base64JdkUtil;
import gtu.file.FileUtil;

public class FileToBase64Util {

	public static void main(String[] args) {
		
	}

	public String fileToBase64(File file) {
		String base64Str = "";
		try {
            byte[] bs = FileUtil.loadFileToByte(file);
            String str = new String(bs, "UTF8");
            base64Str = Base64JdkUtil.encode(str);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
		return base64Str;
	}
	
	public void fileToBase64File(File fromFile, File toTxtFile) {
		String bufferTxt = fileToBase64(fromFile);
		FileUtil.saveToFile(toTxtFile, bufferTxt, "UTF8");
	}
	
	public void base64ToFile(String base64Str, File destFile) {
		try {
            String rtnVal = Base64JdkUtil.decodeToString(base64Str);
            byte[] arry = rtnVal.getBytes("UTF8");
            FileUtil.saveToFile(destFile, arry);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
	}
}
