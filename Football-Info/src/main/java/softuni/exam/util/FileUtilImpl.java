package softuni.exam.util;

import java.io.*;

public class FileUtilImpl implements FileUtil {

    @Override
    public String readFile(String filePath) throws IOException {
        StringBuilder sb = new StringBuilder();

        BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
        String line;
        while ((line = bf.readLine()) != null) {
            sb.append(line)
                    .append(System.lineSeparator());
        }
        return sb.toString();
    }
}
