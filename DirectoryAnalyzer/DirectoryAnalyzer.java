import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DirectoryAnalyzer {
    public static void main(String[] args) throws FileNotFoundException {
        int totalFiles = 0, totalAlphaChars = 0, totalNumericChars = 0, totalSpaceChars = 0, totalSize = 0,
                alphaChars = 0, numericChars = 0, spaceChars = 0;
        if (args.length != 1) {
            System.out.println("Error: One argument needed (directory name).");
            System.exit(0);
        }
        File dir = new File(args[0]);

        String errorMessage = (!dir.exists() ? "Error: Directory does not exist.\n" : "") +
                (!dir.isDirectory() ? "Error: Input is not a directory.\n" : "") +
                (!dir.canRead() ? "Error: Not a readable directory\n" : "");

        if (!errorMessage.isEmpty()) {
            System.out.print(errorMessage);
            System.exit(0);
        }
        File[] files = dir.listFiles();
        System.out.printf("%-30s %-12s %-14s %-16s %-7s\n", "File Name", "Size", "Alpha Chars", "Numeric Chars", "Spaces");

        for (File file : files){
            long size = file.length();
            totalSize += size;
            Scanner scan = new Scanner(file);
            while (scan.hasNext()) {
                String currentLine = scan.nextLine();
                for (int i = 0; i < currentLine.length(); i++) {
                    char c = currentLine.charAt(i);
                    if (Character.isLetter(c)) {
                        alphaChars++;
                    } else if (Character.isDigit(c)) {
                        numericChars++;
                    } else if (c == ' ') {
                        spaceChars++;
                    }
                }
            }
            totalFiles++;
            totalAlphaChars += alphaChars;
            totalNumericChars += numericChars;
            totalSpaceChars += spaceChars;
            System.out.printf("%-30s %-12d %-14d %-16d %-7d\n", file.getName(), size, alphaChars, numericChars, spaceChars);
        }
        System.out.printf("Total Files : %d\n", totalFiles);
        System.out.printf("Total Alpha Chars : %d\n", totalAlphaChars);
        System.out.printf("Total Numeric Chars: %d\n", totalNumericChars);
        System.out.printf("Total Space Chars : %d\n", totalSpaceChars);
        System.out.printf("Total Size Disk: %s\n", formatSize(totalSize));
    }

    private static String formatSize(long size) {
        if (size < 1024) {
            return size + " bytes";
        } else if (size < 1024 * 1024) {
            return String.format("%.2f KB", (double) size / 1024);
        } else if (size < 1024 * 1024 * 1024) {
            return String.format("%.2f MB", (double) size / (1024 * 1024));
        } else {
            return String.format("%.2f GB", (double) size / (1024 * 1024 * 1024));
        }
    }
}
