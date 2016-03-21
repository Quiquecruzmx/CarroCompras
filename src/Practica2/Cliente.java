package Practica2;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente{
    public static void main(String[] args){
     
    }
    
    public static void Conex(InetAddress ip) throws Exception{
        Socket cl=new Socket("localhost",9090);
        ObjectInputStream ois=new ObjectInputStream(cl.getInputStream());
        ObjectOutputStream oos=new ObjectOutputStream(cl.getOutputStream());
        
        System.out.println("Cliente conectado");
        System.out.println("Esperando catalogo");
        int n=ois.readInt();
        System.out.println("Numero de elementos en el catalogo:"+n);
        
        beanArticulos cat[] = new beanArticulos[n];
        for(int i=0;i<n;i++){
            cat[i]= new beanArticulos();
            cat[i]=(beanArticulos) ois.readObject();
        }
        
        Carrito car = new Carrito(cat,ois,oos,cl) {};
        car.setTitle("Carrito de compras");
        car.setLocationRelativeTo(null);
        car.setResizable(true);//False
        car.setVisible(true);
    }
}
