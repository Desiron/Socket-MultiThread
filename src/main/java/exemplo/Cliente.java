package exemplo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
//web
public class Cliente {
    //desenvolvimento
    //outro desenvolvimento
    //outra linha
    //nova linha
    //l1
    //l2
    //l3
    //a4
    //l4
    //l5
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Servidor:");
        String servidor = scan.nextLine();
        System.out.print("Nick:");
        String nick = scan.nextLine();
        
        try (Socket socket = new Socket(servidor, 9000)){
            String cmd;
            OutputStream os = socket.getOutputStream();
            InputStream is = socket.getInputStream();
            
            new Thread(){
            	public void run() {
            		try {
						byte [] buffer = new byte[1024];
						int numRead = -1;
						do{
							numRead = is.read(buffer);
							if (numRead != -1) {
								String linha = new String(buffer, 0, numRead, "utf-8");
								System.out.println(linha);
							}
						}while(numRead != -1);
					} catch (IOException e) {
						e.printStackTrace();
					}
            	}
            }.start();
            
            do{
                cmd = scan.nextLine();
                String msg = String.format("%s: %s\n", nick, cmd);
                os.write(msg.getBytes("utf-8"));
                os.flush();
            }while(!"sair".equalsIgnoreCase(cmd));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
