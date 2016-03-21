package Practica2;

import java.awt.Image;
import java.awt.List;
import java.io.BufferedReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Struct;
import javax.swing.ImageIcon;

public class Servidor{
    public static void main(String args[]) throws Exception{
        
        ServerSocket ss=new ServerSocket(9090);
        int n;
        String catalogo[][];
        System.out.println("Servidor iniciado, esperando clientes");
        
            catalogo=Catalogor();
            n=catalogo[0].length;
            beanArticulos cat[] = new beanArticulos[n];
            
            for(int i=0;i<n;i++){
                cat[i]= new beanArticulos();
                cat[i].setId(Integer.parseInt(catalogo[0][i]));
                cat[i].setNombre(catalogo[1][i]);
                cat[i].setDescripcion(catalogo[2][i]);
                cat[i].setMarca(catalogo[3][i]);
                cat[i].setPrecio(Float.parseFloat(catalogo[4][i]));
                cat[i].setExistencias(Integer.parseInt(catalogo[5][i]));
                cat[i].setFoto_producto(new ImageIcon("Imagenes/"+catalogo[6][i]));
            }

        while(true){
            Socket cl=ss.accept();
            System.out.println("Cliente conectado desde:"+cl.getInetAddress()+":"+cl.getPort());
            
            ObjectOutputStream oos=new ObjectOutputStream(cl.getOutputStream());
            ObjectInputStream ois=new ObjectInputStream(cl.getInputStream());
            
            
            oos.writeInt(n);
            oos.flush();
            
            for(int i=0;i<n;i++){
                oos.writeObject(cat[i]);
                oos.flush();
            }
            
            
            
            int[] agregados=null;
            agregados = (int[]) ois.readObject();
            cat =(beanArticulos[]) ois.readObject();
            int i =0;
            float total=0;
            
            while(i<agregados.length){
                total= total + cat[agregados[i++]].getPrecio();
            }
            String env = "Total a pagar: $"+total;
            oos.writeObject(env);
            System.out.println("Cerrando comunicacion");
            
            ois.close();
            oos.close();
            cl.close();
        }//8.25.100.100 Hacer envios de archivos datagrama (Practica 1 con sockets datagrama) y Practica 2
        
    }
    
    public static String[][] Catalogor() throws FileNotFoundException, IOException{
        int i=0;
        List l = new List();
        String catalogo[][],aux[]=new String[4];
        File cat=new File("Catalogo.txt");
        BufferedReader entrada=new BufferedReader(new FileReader(cat));
        while(entrada.ready()){
            l.add(entrada.readLine());
            i++;
        }
        
        catalogo=new String[7][i];
        for(int j=0;j<i;j++){
            aux=l.getItem(j).split(" ");
            catalogo[0][j]=aux[0];
            catalogo[1][j]=aux[1];
            catalogo[2][j]=aux[2];
            catalogo[3][j]=aux[3];
            catalogo[4][j]=aux[4];
            catalogo[5][j]=aux[5];
            catalogo[6][j]=aux[6];
        }
        entrada.close();
        return catalogo;
    }
}