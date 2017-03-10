package exemplo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor {

    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(9000);
        while(true){

            Socket socket = server.accept();

            new Thread(){
                public void run() {
                    try {
                        System.out.println("Alguem conectou");
                        Scanner scan = new Scanner(socket.getInputStream());
                        while(scan.hasNext()){
                            String linha = scan.nextLine();
                            System.out.println(linha);
                            if("sair".equalsIgnoreCase(linha)){
                                break;
                            }
                        }
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }                
                }
            }.start();

        }



    }

}
