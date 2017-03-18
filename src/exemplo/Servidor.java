package exemplo;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Servidor {

    public static void main(String[] args) throws IOException {
    	
    	List<OutputStream> saidas = new ArrayList<>();
    	
        ServerSocket server = new ServerSocket(9000);
        while(true){

            Socket socket = server.accept();
            OutputStream os = socket.getOutputStream();
            saidas.add(os);
            
            new Thread(){
                public void run() {
                    try {
                        System.out.println("Alguem conectou");
                        Scanner scan = new Scanner(socket.getInputStream(), "utf-8");
                        while(scan.hasNext()){
                            String linha = scan.nextLine();
                            System.out.println(linha);
                            for(OutputStream osUsu : saidas ){
                            	osUsu.write(linha.getBytes("utf-8"));
                            	osUsu.flush();
                            }
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
