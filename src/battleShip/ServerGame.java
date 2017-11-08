package battleShip;

import java.net.DatagramPacket;
import java.net.InetAddress;

class ServerGame
{
	ServerNetWorkIO IO;
	private InetAddress[] IPAddress;
	private String[] names;
	private int ports[];
	private JShipGame game;
	boolean shipIDs[];
	boolean activeGame;
	public ServerGame(int port)
	{
		IO = new ServerNetWorkIO(port);
		IPAddress = new InetAddress[2];
		names = new String[2];
		ports = new int[2];
		shipIDs = new boolean[]{false, false};
		activeGame = false;
	}

	void start()
	{
		while(true)
		{
			System.out.println("Waiting for packet...");
			DatagramPacket packet = IO.getPacket();
			if(packet != null)
			{
				ProccessInput(packet);
			}
		}
	}

	public void ProccessInput(DatagramPacket packet)
	{
		String packetMessage = new String(packet.getData());
		String name = packetMessage.substring(0, packetMessage.indexOf(':'));
		String message = packetMessage.substring(packetMessage.indexOf(':')+1, packetMessage.indexOf(0));
		int PlayerIndex = GetIndex(packet.getAddress(), name);
		String result="";
		if(PlayerIndex == -1)
		{
			if(message.equals("join"))
			{ 
				PlayerIndex = CreatePlayerindex(packet.getAddress(), name, packet.getPort());
				if(PlayerIndex == -1)
				{
					result = "bad:Server full";
				}
				else
				{
					result = "good:Welcome to the server, "+name+"!";
				}
				if(!(IPAddress[0] == null) && !(IPAddress[1] == null))
				{
					activeGame = true;
					game = new JShipGame();
				}
			}
			
		}
		else
		{
			if(message.equals("quit") && PlayerIndex != -1)
			{
				RemovePlayerindex(PlayerIndex);
				activeGame = false;
				shipIDs = new boolean[]{false, false};
				IO.sendPacket(new DatagramPacket(result.getBytes(), result.getBytes().length, packet.getAddress(), packet.getPort()));
				if(IPAddress[(PlayerIndex+1)%2] == null)
				{
					result = "reset:Server reset, player left";
					IO.sendPacket(new DatagramPacket(result.getBytes(), result.getBytes().length, IPAddress[(PlayerIndex+1)%2], ports[(PlayerIndex+1)%2]));
				}
				return;
			}
			else
			{
				if(!activeGame)
				{
					result = "bad:Waiting for additonal players";
				}
				else
				{
					result = processMove(PlayerIndex, name, message);
				}
				if(result.substring(0, result.indexOf(":")).equals("win"))
				{
					String out = "lose:You Lose!!";
					IO.sendPacket(new DatagramPacket(out.getBytes(), out.getBytes().length, IPAddress[(PlayerIndex+1)%2], ports[(PlayerIndex+1)%2]));
					IPAddress = new InetAddress[2];
					names = new String[2];
					ports = new int[2];
					shipIDs = new boolean[]{false, false};
					activeGame = false;
				}
			}
		}
		IO.sendPacket(new DatagramPacket(result.getBytes(), result.getBytes().length, packet.getAddress(), packet.getPort()));
	}

	private int CreatePlayerindex(InetAddress IP, String name, int port)
	{
		for(int i = 0; i < IPAddress.length; i++)
		{
			if(IPAddress[i] == null)
			{
				IPAddress[i] = IP;
				names[i] = name;
				ports[i] = port;
				System.out.println("Player: "+name+" joined from: "+IP);
				return i;
			}
		}
		return -1;
	}

	private void RemovePlayerindex(int Index)
	{
		if(Index != -1)
		{
			IPAddress[Index] = null;
			names[Index] = null;
			ports[Index] = 0;
		}
	}

	private int GetIndex(InetAddress IP, String name)
	{
		for(int i = 0; i < IPAddress.length; i++)
		{
			if(IPAddress[i] != null && IPAddress[i].equals(IP) && name.equals(names[i]))
				return i;
		}
		return -1;
	}

	private String processMove(int PlayerIndex, String name, String move)
	{
		if(shipIDs[PlayerIndex] == false)
		{
			game.assignShips(PlayerIndex, move);
			shipIDs[PlayerIndex] = true;
			return "good:Ships succesfully placed!";
		}
		else
		{
			if(!shipIDs[(PlayerIndex+1)%2])
			{
				return "bad:Waiting for other player to place ships";
			}
			int x = move.charAt(0)-97;
			int y = move.charAt(1)-48;
			int result = game.MakeMove(PlayerIndex, x, y);
			String out;
			switch(result)
			{
				case -2:
					out = "bad:It's not your turn right now!!"; break;
				case -1:
					out = "bad:You already attacked this spot!"; break;
				case 0:
					out = "good:MISS!!"; break;
				case 1:
					out = "good:HIT!!"; break;
				case 2:
					out = "win:You Win!!"; break;
				default:
					out = "error:Internal Error while makeing move"; break;
			}
			return out+'\n'+ProduceBoard(PlayerIndex);
		}
	}

	public String ProduceBoard(int PlayerIndex)
	{
		char[][][] boards = game.getPlayerView(PlayerIndex);
		String result = "Your board: \n";
		result += " abcdefghij\n";
		for(int i = 0; i < 10; i++)
		{
			result += i;
			for(int j= 0; j < 10; j++)
			{
				result += boards[0][j][i];
			}
			result += '\n';
		}
		result += "Enemy board: \nabcdefghij\n";
		for(int i = 0; i < 10; i++)
		{
			result += i;
			for(int j= 0; j < 10; j++)
			{
				result += boards[1][j][i];
			}
			result += '\n';
		}
		return result;
	}
}

