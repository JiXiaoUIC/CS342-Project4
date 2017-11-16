package battleShip;

import java.net.*; 
import java.io.*; 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameServer extends JFrame implements ActionListener{
  
  // GUI items
  JButton ssButton;
  JLabel machineInfo;
  JLabel portInfo;
  JTextArea history;
  private boolean running;

  // Network Items
  boolean serverContinue;
  ServerSocket serverSocket;

   // set up GUI
   public GameServer()
   {
      super( "Echo Server" );

      // get content pane and set its layout
      Container container = getContentPane();
      container.setLayout( new FlowLayout() );

      // create buttons
      running = false;
      ssButton = new JButton( "Start Listening" );
      ssButton.addActionListener( this );
      container.add( ssButton );

      String machineAddress = null;
      try
      {  
        InetAddress addr = InetAddress.getLocalHost();
        machineAddress = addr.getHostAddress();
      }
      catch (UnknownHostException e)
      {
        machineAddress = "127.0.0.1";
      }
      machineInfo = new JLabel (machineAddress);
      container.add( machineInfo );
      portInfo = new JLabel (" Not Listening ");
      container.add( portInfo );

      history = new JTextArea ( 10, 40 );
      history.setEditable(false);
      container.add( new JScrollPane(history) );

      setSize( 500, 250 );
      setVisible( true );

   } // end CountDown constructor

//   public static void main( String args[] )
//   { 
//	   GameServer application = new GameServer();
//      application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
//   }

    // handle button event
    public void actionPerformed( ActionEvent event )
    {
       if (running == false)
       {
         new ConnectionThread (this);
       }
       else
       {
         serverContinue = false;
         ssButton.setText ("Start Listening");
         portInfo.setText (" Not Listening ");
       }
    }


 } // end class EchoServer3


class ConnectionThread extends Thread
 {
	GameServer gui;
   
   public ConnectionThread (GameServer es3)
   {
     gui = es3;
     start();
   }
   
   public void run()
   {
     gui.serverContinue = true;
     
     try 
     { 
       gui.serverSocket = new ServerSocket(0); 
       gui.portInfo.setText("Listening on Port: " + gui.serverSocket.getLocalPort());
       System.out.println ("Connection Socket Created");
       try { 
         while (gui.serverContinue)
         {
           System.out.println ("Waiting for Connection");
           gui.ssButton.setText("Stop Listening");
           new CommunicationThread (gui.serverSocket.accept(), gui); 
         }
       } 
       catch (IOException e) 
       { 
         System.err.println("Accept failed."); 
         System.exit(1); 
       } 
     } 
     catch (IOException e) 
     { 
       System.err.println("Could not listen on port: 10008."); 
       System.exit(1); 
     } 
     finally
     {
       try {
         gui.serverSocket.close(); 
       }
       catch (IOException e)
       { 
         System.err.println("Could not close port: 10008."); 
         System.exit(1); 
       } 
     }
   }
 }
 

class CommunicationThread extends Thread
{ 
 //private boolean serverContinue = true;
 private Socket clientSocket;
 private GameServer gui;
 private int count = 0;




 public CommunicationThread (Socket clientSoc, GameServer ec3)
   {
    clientSocket = clientSoc;
    gui = ec3;
    gui.history.insert ("Comminucating with Port" + clientSocket.getLocalPort()+"\n", 0);
    start();
   }

 public void run()
   {
    System.out.println ("New Communication Thread Started");
    try { 
         PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), 
                                      true); 
         BufferedReader in = new BufferedReader( 
                 new InputStreamReader( clientSocket.getInputStream())); 

         String inputLine; 
       
         while ((inputLine = in.readLine()) != null) 
             { 
              System.out.println ("Server: " + inputLine); 
              gui.history.insert (inputLine+"\n", 0);
              out.println(inputLine); 

              if (inputLine.equals("Bye.")) 
                  break; 

              if (inputLine.equals("End Server.")) 
                  gui.serverContinue = false; 
             } 

         out.close(); 
         in.close(); 
         clientSocket.close(); 
        } 
    catch (IOException e) 
        { 
         System.err.println("Problem with Communication Server");
         //System.exit(1); 
        } 
    }
} 





 

