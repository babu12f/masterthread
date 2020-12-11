package com.babor.runtime.cmd;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class FirstCmdCommand {

    public static void main(String[] args) {
        try {
            //Process process = Runtime.getRuntime().exec("ping www.stackabuse.com");
//            Process process = Runtime.getRuntime().exec(
//                    "cmd /c start /b create.bat",
//                    null,
//                    new File("C:\\Users\\BABOR\\Desktop\\"));
//            Process process = Runtime.getRuntime().exec(
//                    "cmd /c start cmd /c create.bat && dir",
//                    null,
//                    new File("C:\\Users\\BABOR\\Desktop\\"));
//
              Process process = Runtime.getRuntime().exec(
                    "cmd /c start cmd /c create.bat && dir",
                    null,
                    new File("C:\\Users\\BABOR\\Desktop\\"));
            process.waitFor();
            process.getOutputStream().close();
            printResults(process);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void printResults(Process process) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = "";
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }

}
