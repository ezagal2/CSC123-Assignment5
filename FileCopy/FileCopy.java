import java.io.*;

public class FileCopy {
    public static void main(String[] args) throws IOException {
        if(args.length < 2){
            System.out.println("Error: Two Arguments Needed");
            System.exit(0);
        }
        File sourceFile = new File(args[0]);
        File targetFile = new File(args[1]);

        String errorMessage = (!sourceFile.exists() ? "Error: Source file does not exist.\n" : "") +
                (sourceFile.isDirectory() ? "Error: Source file is a directory.\n" : "") +
                (targetFile.exists() ? "Error: Target file already exists.\n" : "") +
                (targetFile.isDirectory() ? "Error: Target file is a directory.\n" : "");

        if (!errorMessage.isEmpty()) {
            System.out.print(errorMessage);
            System.exit(0);
        }

        File targetDirectory = targetFile.getParentFile();
        if (targetDirectory != null && !targetDirectory.exists()) {
            targetDirectory.mkdirs();
        }

        FileInputStream fileInputStream = new FileInputStream(sourceFile);
        FileOutputStream fileOutputStream = new FileOutputStream(targetFile);
        byte[] bytes = fileInputStream.readAllBytes();
        fileOutputStream.write(bytes);
        System.out.printf("%d bytes successfully copied from %s to %s\n", sourceFile.length(), args[0], args[1]);
        fileInputStream.close();
        fileOutputStream.close();
    }
}
