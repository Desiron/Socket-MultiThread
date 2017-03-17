package exemplo;

import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

//modificado da web

public class Cliente {
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Servidor:");
        String servidor = scan.nextLine();
        System.out.print("Nick:");
        String nick = scan.nextLine();
        
        try (Socket socket = new Socket(servidor, 9000)){
            String cmd;
            OutputStream os = socket.getOutputStream();
            do{
                cmd = scan.nextLine();
                String msg = String.format("%s: %s\n", nick, cmd);
                System.out.println(msg);
                os.write(msg.getBytes("utf-8"));
                os.flush();
            }while(!"sair".equalsIgnoreCase(cmd));
        } catch (Exception e) {
        	//commit do eclipse
            e.printStackTrace();
        }
    }

}
