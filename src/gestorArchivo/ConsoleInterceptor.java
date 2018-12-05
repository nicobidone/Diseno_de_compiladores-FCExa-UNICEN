/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestorArchivo;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 *
 * @author nicob
 */
public class ConsoleInterceptor {

    public interface Block {
        void call() throws Exception;
    }

    public static String copyOut(Block block) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(bos, true);
        PrintStream oldStream = System.out;
        System.setOut(printStream);
        try {
            block.call();
        }
        finally {
            System.setOut(oldStream);
        }
        return bos.toString();
    }
}