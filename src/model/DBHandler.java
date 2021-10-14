package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

public class DBHandler {
	private static Connection con;
	private static String host = "localhost";
	private static int port = 3307;
	private static String dbName = "hotel_management_system";
	private static String userName = "root";
	private static String password = "root";
	
	
//	**************Start Connection ***************
	public static boolean openConnection()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://"+host+":"+port+"/"+dbName;
			con = DriverManager.getConnection(url, userName, password);
			return true;
		}catch(Exception e)
		{
			return false;
		}
	}
	
	public static boolean closeConnection()
	{
		try {
			con.close();
			return true;
			
		}catch(Exception e)
		{
			return false;
		}
	}
	
//	**************End Connection **********************
	
	
//	******************Queries For Admins CRUD ***********************
	
	
	public static ArrayList<Admin> getAllAdmins()
	{
		int no=1;
		ArrayList<Admin>admins = new ArrayList<Admin>();
		try {
			openConnection();
			String query= "Select admin_id,name,staff_id,phone,nrc,address from admins where delete_date is null";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Admin adm = new Admin(no++,rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),
									rs.getString(6));
				admins.add(adm);
			}
			closeConnection();
		}catch(Exception e)
		{
			
		}
		return admins;
	}
	
	public static ArrayList<String> getAdminStaffId()
	{
		ArrayList<String> staffId = new ArrayList<String>();
		
		try {
			openConnection();
			String query= "Select staff_id from admins where delete_date is null";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				String sid = rs.getString(1);
				staffId.add(sid);
			}
			closeConnection();
		}catch(Exception e)
		{
			
		}
		return staffId;
	}
	
	public static boolean updateAdmin(int id,String n,String sid,String ph,String nrc,String ad)
	{
		
		try {
			openConnection();
			String sql= "Update admins set name=?,staff_id=?,phone=?,nrc=?,address=? where admin_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, n);
			ps.setString(2, sid);
			ps.setString(3, ph);
			ps.setString(4, nrc);
			ps.setString(5, ad);
			ps.setInt(6, id);
			int line = ps.executeUpdate();
			closeConnection();
			return line>0;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean addAdmin(String name,String sid,String phone,String nrc,String add,String pass)
	{
		pass = ValidChecker.digestMsg(pass);
		try {
			openConnection();
			String sql = "Insert into admins(name,staff_id,phone,nrc,address,password) values(?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, sid);
			ps.setString(3, phone);
			ps.setString(4, nrc);
			ps.setString(5, add);
			ps.setString(6, pass);
			int line = ps.executeUpdate();
			closeConnection();
			return line>0;
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean addAdminDeleteDate(int id)
	{
		try {
			openConnection();
			String sql = "Update admins set delete_date=curdate() where admin_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			int line = ps.executeUpdate();
			closeConnection();
			return line>0;
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public static int loginAdmin(String sid,String pw)
	{
		int id=-1;
		try {
			openConnection();
			pw=ValidChecker.digestMsg(pw);
			String sql="Select admin_id,name from admins where staff_id=? and password=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, sid);
			ps.setString(2, pw);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				id = rs.getInt(1);
			}
		}catch(Exception e)
		{
			
		}
		closeConnection();
		return id;
	}
	
	public static String getLoginAdminName(int id)
	{
		String name=null;
		try {
			openConnection();
			String sql="Select name from admins where admin_id=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				name = rs.getString(1);
			}
		}catch(Exception e)
		{
			
		}
		closeConnection();
		return name;
	}

	public static String getLoginAdminPassword(int id)
	{
		String password=null;
		try {
			openConnection();
			String sql="Select password from admins where admin_id=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				password = rs.getString(1);
			}
		}catch(Exception e)
		{
			
		}
		closeConnection();
		return password;
	}

	
	public static void changeAdminPassword(int id,String pw)
	{
		try {
			openConnection();
			pw = ValidChecker.digestMsg(pw);
			String sql = "Update admins set password=? where admin_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, pw);
			ps.setInt(2, id);
			int line = ps.executeUpdate();
			closeConnection();
			
		}catch(Exception e)
		{
			
		}
	}
	
	public static void changeReceptionistPassword(int id,String pw)
	{
		try {
			openConnection();
			pw = ValidChecker.digestMsg(pw);
			String sql = "Update receptionists set password=? where receptionist_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, pw);
			ps.setInt(2, id);
			int line = ps.executeUpdate();
			closeConnection();
			
		}catch(Exception e)
		{
			
		}
	}
	
	public static boolean deleteAdmin(int id)
	{
		try {
			openConnection();
//			deleteGuestCheckIn(id);
			String sql= "Delete from admins where admin_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			int line = ps.executeUpdate();
			closeConnection();
			return line>0;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	
//***************	End Queries for Admins CRUD ***************************

	

//	*************Start Queries For Receptionists CRUD *******************************
	
	
	public static boolean addReceptionist(String name,String sid,String ph,String nrc,String ad,String pw)
	{
		pw = ValidChecker.digestMsg(pw);
		try {
			openConnection();
			String sql = "Insert into receptionists(name,staff_id,phone,nrc,address,password) values(?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, sid);
			ps.setString(3, ph);
			ps.setString(4, nrc);
			ps.setString(5, ad);
			ps.setString(6, pw);
			int line = ps.executeUpdate();
			closeConnection();
			return line>0;
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean addReceptionistDeleteDate(int id)
	{
		try {
			openConnection();
			String sql = "Update receptionists set delete_date=curdate() where receptionist_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			int line = ps.executeUpdate();
			closeConnection();
			return line>0;
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public static int loginReceptionist(String sid,String pw)
	{
		int id=-1;
		try {
			openConnection();
			pw=ValidChecker.digestMsg(pw);
			String sql="Select receptionist_id from receptionists where staff_id=? and password=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, sid);
			ps.setString(2, pw);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				id = rs.getInt(1);
				
			}
		}catch(Exception e)
		{
			
		}
		closeConnection();
		return id;
	}
	
	public static String getLoginReceptionistName(int id)
	{
		String name=null;
		try {
			openConnection();
			String sql="Select name from receptionists where receptionist_id=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				name = rs.getString(1);
			}
		}catch(Exception e)
		{
			
		}
		closeConnection();
		return name;
	}
	
	public static String getLoginReceptionistPassword(int id)
	{
		String pw=null;
		try {
			openConnection();
			String sql="Select password from receptionists where receptionist_id=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				pw = rs.getString(1);
			}
		}catch(Exception e)
		{
			
		}
		closeConnection();
		return pw;
	}
	
	public static ArrayList<LogBooks> getReceptionistDetails()
	{
		ArrayList<LogBooks> logBooks = new ArrayList<LogBooks>();
		try {
			openConnection();
			String query= "SELECT rd.receptionist_detail_id,r.name,rd.login_date,rd.login_time,rd.logout_date,\r\n"
					+ "rd.logout_time \r\n"
					+ "FROM receptionists r\r\n"
					+ "right JOIN receptionist_details rd ON r.receptionist_id=rd.receptionist_id";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				LogBooks lb = new LogBooks(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),
						rs.getString(5),rs.getString(6));
				logBooks.add(lb);
			}
			closeConnection();
		}catch(Exception e)
		{
			
		}
		return logBooks;
	}
	
	public static ArrayList<Receptionist> getAllReceptionists()
	{
		int no=1;
		ArrayList<Receptionist> receptionists = new ArrayList<Receptionist>();
		try
		{
			openConnection();
			String query = "Select receptionist_id,name,staff_id,phone,nrc,address from receptionists where delete_date is null";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Receptionist recept = new Receptionist(no++,rs.getInt(1),rs.getString(2),
						rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
				receptionists.add(recept);
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return receptionists;
	}
	
	public static boolean updateReceptionists(int receptionist_id,String name,String staffId,String phone,String nrc,String address)
	{
		try {
			openConnection();
			String sql= "Update receptionists set name=?,staff_id=?,phone=?,nrc=?,address=? where receptionist_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, staffId);
			ps.setString(3, phone);
			ps.setString(4, nrc);
			ps.setString(5, address);
			ps.setInt(6, receptionist_id);
			int line = ps.executeUpdate();
			closeConnection();
			return line>0;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public static boolean deleteReceptionist(int id)
	{
		try {
			openConnection();
//			deleteGuestCheckIn(id);
			String sql= "Delete from receptionists where receptionist_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			int line = ps.executeUpdate();
			closeConnection();
			return line>0;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public static boolean addReceptionistLoginTime(int id,String loginTime)
	{
		try {
			openConnection();
			String sql = "Insert into receptionist_details(receptionist_id,login_date,login_time)"
					+ "values(?,curdate(),?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,id);
			ps.setString(2, loginTime);
			int line = ps.executeUpdate();
			closeConnection();
			return line>0;
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean addReceptionistLogoutTime(int id,String logoutTime)
	{
		try {
			openConnection();
			String sql = "Update receptionist_details set logout_date=curdate(),logout_time=? where receptionist_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, logoutTime);
			ps.setInt(2, id);
			int line = ps.executeUpdate();
			closeConnection();
			return line>0;
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public static ArrayList<String> getReceptionistStaffId()
	{
		ArrayList<String> staffId = new ArrayList<String>();
		
		try {
			openConnection();
			String query= "Select staff_id from receptionists where delete_date is null";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				String sid = rs.getString(1);
				staffId.add(sid);
			}
			closeConnection();
		}catch(Exception e)
		{
			
		}
		return staffId;
	}
	
	
	
//	***********************End Queries for Receptionists CRUD*************************
	
	
	
	
//	**********Start Queries For Guests ******************************
	

	public static ArrayList<Guest> getAllGuests()
	{
		int no=1;
		ArrayList<Guest> guest = new ArrayList<Guest>();
	
		try {
			openConnection();
			String query="Select * from guests";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				Guest g = new Guest(no++,rs.getInt(1),rs.getString(2),rs.getNString(3),rs.getString(4),rs.getString(5),rs.getString(6));
				guest.add(g);
			}
			closeConnection();
		}catch(Exception e) 
		{
			
		}
		return guest;
	}
	
	public static ArrayList<GuestProfile> getAllGuestProfile()
	{
		int No=1;
		ArrayList<GuestProfile> guestProfile = new ArrayList<GuestProfile>();
		
		try {
			openConnection();
			String query="SELECT rooms.room_no,guests.name,guests.phone,guests.nrc,guests.email,guests.address,check_in_date,check_out_date\r\n"
					+ "FROM guests INNER JOIN check_in_out ON guests.guest_id = check_in_out.guest_id\r\n"
					+ "INNER JOIN reserve_rooms ON reserve_rooms.reserve_room_id=check_in_out.reserve_room_id\r\n"
					+ "INNER JOIN rooms ON reserve_rooms.room_id=rooms.room_id";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				GuestProfile gp = new GuestProfile(No++,rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),
													rs.getString(5),rs.getString(6),rs.getString(7), rs.getString(8));
				guestProfile.add(gp);
			}
			closeConnection();
		}catch(Exception e) 
		{
		}
		return guestProfile;
	}
	
	public static boolean insertGuests(String n,String ph,String nrc,String em,String ad)
	{
		try {
			openConnection();
			String sql = "Insert into guests(name,nrc,phone,email,address) values(?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, n);
			ps.setString(2, ph);
			ps.setString(3, nrc);
			ps.setString(4, em);
			ps.setString(5, ad);
			int line = ps.executeUpdate();
			closeConnection();
			return line>0;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	public static boolean deleteGuests(int id)
	{
		try {
			openConnection();
//			deleteGuestCheckIn(id);
			String sql= "Delete from guests where guest_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			int line = ps.executeUpdate();
			closeConnection();
			return line>0;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public static boolean insertCustomer(String name,String phone)
	{
		try {
			openConnection();
			String sql = "Insert into customers(name,phone)values(?,?)";
					
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, phone);
			int line = ps.executeUpdate();
			closeConnection();
			return line>0;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public static boolean fillGuestInfo(int gid,String name,String ph,String nrc,String mail,String address)
	{
		try {
			openConnection();
			String sql = "Update guests set name=?,phone=?,nrc=?,email=?,address=? where guest_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, ph);
			ps.setString(3, nrc);
			ps.setString(4,mail);
			ps.setString(5, address);
			ps.setInt(6, gid);
			int line = ps.executeUpdate();
			closeConnection();
			return line>0;
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	
	
//	*******************End Quereis For Guests ***********************
	
	
	
	//	*************Start Queries For Payment CRUD****************
	

	public static ArrayList<Payment> getPayment()
	{
		ArrayList<Payment> pay = new ArrayList<Payment>();
		
		try {
			openConnection();
			String query="Select* from payments";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				Payment p = new Payment(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getInt(5));
				pay.add(p);
			}
			closeConnection();
		}catch(Exception e) 
		{
			
		}
		return pay;
	
	}
	
	public static boolean insertPayment(String payDate,int payAmount,int payTypeId,int rrid)
	{
		
		try {
			openConnection();
			String sql = "Insert into payments (payment_date,payment_amount,payment_type_id,reserve_room_id) VALUES(?,?,?,?)";
					
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, payDate);
			ps.setInt(2, payAmount);
			ps.setInt(3, payTypeId);
			ps.setInt(4, rrid);
			int line = ps.executeUpdate();
			closeConnection();
			return line>0;
		}
		catch(Exception e)
		{
			return false;
		}
		
	}
	
	public static int getPaymentTypeId(String type)
	{
		int ans=0;
		
		try {
			openConnection();
			String query="Select payment_type_id from payment_types where payment_type=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, type);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next())
			{
				ans = rs.getInt(1);
			}
				closeConnection();
				return ans;
			}catch(Exception e) 
			{
				e.printStackTrace();
			}
			return ans;
	}
	
	public static ArrayList<PaymentType> getAllPaymentTypes()
	{
		ArrayList<PaymentType> pt = new ArrayList<PaymentType>();
		try {
			openConnection();
			String query="Select payment_type from payment_types";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				PaymentType p = new PaymentType(rs.getString(1));
				pt.add(p);
			}
			closeConnection();
		}catch(Exception e) 
		{
			
		}
		return pt;
	}
	
	public static boolean insertPaymentType(int paymentType,String name,String ph,String acc)
	{
		try {
			openConnection();
			String sql = "Insert into payment_types (payment_type,name,phone,account) VALUES(?,?,?,?)";
					
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, paymentType);
			ps.setString(2, name);
			ps.setString(3, ph);
			ps.setString(4,acc);
			int line = ps.executeUpdate();
			closeConnection();
			return line>0;
		}
		catch(Exception e)
		{
			return false;
		}
		
	}
	
	
//*****************ENd Queries For Payment CRUD ********************
	
	
	// ******************Select current ID and next ID ********************
	public static int getNextCustomerId()
	{	
		int ans=1;
	
		try {
			openConnection();
			String query="Select max(customer_id) from customers";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next())
			{
				ans += rs.getInt(1);
			}
				closeConnection();
				return ans;
			}catch(Exception e) 
			{
				
			}
			return ans;
	}
	
	public static int getCurrentCustomerId(String cusName,String cusPh)
	{
		int ans=0;
		
		try {
			openConnection();
			String query="Select max(customer_id) from customers where name=? and phone=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1,cusName );
			ps.setString(2, cusPh);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next())
			{
				ans = rs.getInt(1);
			}
				closeConnection();
				return ans;
			}catch(Exception e) 
			{
				
			}
			return ans;
	}
	
	public static int getCurrentBedTypeId(String type)
	{
		int ans=0;
		
		try {
			openConnection();
			String query="Select max(bed_type_id) from bed_types where bed_type=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1,type);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next())
			{
				ans = rs.getInt(1);
			}
				closeConnection();
				return ans;
			}catch(Exception e) 
			{
				
			}
			return ans;
	}
	
	public static int getCurrentReserveRoomsId()
	{
		int ans=0;
		
		try {
			openConnection();
			String query="Select max(reserve_room_id) from reserve_rooms";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next())
			{
				ans = rs.getInt(1);
			}
				closeConnection();
				return ans;
			}catch(Exception e) 
			{
				
			}
			return ans;
	}
	
	public static int getReserveRoomsId()
	{
		int ans=0;
		
		try {
			openConnection();
			String query="Select reserve_room_id from reserve_rooms";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next())
			{
				ans += rs.getInt(1);
			}
				closeConnection();
				return ans;
			}catch(Exception e) 
			{
				
			}
			return ans;
	}
	
	public static int getNextReserveRoomsId()
	{
		int ans=1;
		
		try {
			openConnection();
			String query="Select max(reserve_room_id) from reserve_rooms";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next())
			{
				ans += rs.getInt(1);
			}
				closeConnection();
				return ans;
			}catch(Exception e) 
			{
				
			}
			return ans;
	}
	
	public static int getNextGuestId()
	{
		int ans=1;
		
		try {
			openConnection();
			String query="Select max(guest_id) from guests";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next())
			{
				ans += rs.getInt(1);
			}
				closeConnection();
				return ans;
			}catch(Exception e) 
			{
				
			}
			return ans;
	}
	
	public static int getNextReserveId()
	{	
		int ans=1;
	
		try {
			openConnection();
			String query="Select max(reserve_id) from reservations";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next())
			{
				ans += rs.getInt(1);
			}
				closeConnection();
				return ans;
			}catch(Exception e) 
			{
				
			}
			return ans;
	}

	public static int getCurrentReserveId(String rDate,int depo,int reTypeId,int receptionId,int cusId)
	{
		int ans=0;
		
		try {
			openConnection();
			String query="Select max(reserve_id) from reservations where reserve_date=? and deposit=? and "
					+ "reserve_type_id=? and receptionist_id=? and customer_id=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, rDate);
			ps.setInt(2, depo);
			ps.setInt(3, reTypeId);
			ps.setInt(4,receptionId);
			ps.setInt(5, cusId);
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next())
			{
				ans = rs.getInt(1);
			}
				closeConnection();
				return ans;
			}catch(Exception e) 
			{
				e.printStackTrace();
			}
			return ans;
	}
	
	public static int getReserveId(String rDate,int depo,int reTypeId,int receptionId,int cusId)
	{
		int ans=0;
		
		try {
			openConnection();
			String query="Select reserve_id from reservations where reserve_date=? and deposit=? and "
					+ "reserve_type_id=? and receptionist_id=? and customer_id=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, rDate);
			ps.setInt(2, depo);
			ps.setInt(3, reTypeId);
			ps.setInt(4,receptionId);
			ps.setInt(5, cusId);
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next())
			{
				ans = rs.getInt(1);
			}
				closeConnection();
				return ans;
			}catch(Exception e) 
			{
				e.printStackTrace();
			}
			return ans;
	}
	
	public static int currentReservationId()
	{
		int ans=0;
		
		try {
			openConnection();
			String query="Select max(reserve_id) from reservations";
			PreparedStatement ps = con.prepareStatement(query);
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next())
			{
				ans = rs.getInt(1);
			}
				closeConnection();
				return ans;
			}catch(Exception e) 
			{
				e.printStackTrace();
			}
			return ans;
	}
	
	public static int getNextPaymentId()
	{	
		int ans=1;
	
		try {
			openConnection();
			String query="Select max(payment_id) from payments";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next())
			{
				ans += rs.getInt(1);
			}
				closeConnection();
				return ans;
			}catch(Exception e) 
			{
				
			}
			return ans;
	}
	
	public static int getCurrentPaymentId()
	{
		int ans=0;
		
		try {
			openConnection();
			String query="Select max(payment_id) from payments";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next())
			{
				ans = rs.getInt(1);
			}
				closeConnection();
				return ans;
			}catch(Exception e) 
			{
				
			}
			return ans;
	}
	
	public static int getCurrentGuestId(String n,String ph) {
		int ans=0;
		
		try {
			openConnection();
			String query="Select max(guest_id) from guests where name=? and phone=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, n);
			ps.setString(2, ph);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next())
			{
				ans = rs.getInt(1);
			}
				closeConnection();
				return ans;
			}catch(Exception e) 
			{
				e.printStackTrace();
			}
			return ans;
	}
	
	public static int getGuestId(String n,String ph) {
		int ans=0;
		
		try {
			openConnection();
			String query="Select guest_id from guests where name=? and phone=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, n);
			ps.setString(2, ph);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next())
			{
				ans = rs.getInt(1);
			}
				closeConnection();
				return ans;
			}catch(Exception e) 
			{
				e.printStackTrace();
			}
			return ans;
	}
	
	
//	******************End Select current Id and next id *********************************************************
	
	

	
//	****************Start Queries for Rooms , Room Types, BedType***************************
	
	
	public static ArrayList<RoomNo> getRoomNo(int rtId)
	{
		ArrayList<RoomNo> roomNos = new ArrayList<RoomNo>();
	
		try {
			openConnection();
			String query="Select room_no from rooms where rooms.room_type_id=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, rtId);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				RoomNo ro = new RoomNo(rs.getInt(1));
				roomNos.add(ro);
			}
			closeConnection();
		}catch(Exception e) 
		{
			
		}
		return roomNos;
	}
	
	public static ArrayList<RoomNo> getAllRoomNo()
	{
		ArrayList<RoomNo> roomNos = new ArrayList<RoomNo>();
	
		try {
			openConnection();
			String query="Select room_no from rooms";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				RoomNo ro = new RoomNo(rs.getInt(1));
				roomNos.add(ro);
			}
			closeConnection();
		}catch(Exception e) 
		{
			
		}
		return roomNos;
	}
	
	public static ArrayList<RoomType> getAllRoomType()
	{
		ArrayList<RoomType> roomTypes = new ArrayList<RoomType>();
	
		try {
			openConnection();
			String query="Select room_type_id,room_type from room_types";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				RoomType rt = new RoomType(rs.getInt(1),rs.getString(2));
				roomTypes.add(rt);
			}
			closeConnection();
		}catch(Exception e) 
		{
			
		}
		return roomTypes;
	}
	
	public static ArrayList<BedType> getAllBedType(String rt)
	{
		ArrayList<BedType> bedTypes = new ArrayList<BedType>();
	
		try {
			openConnection();
			String query="SELECT bed_type FROM bed_types AS bt INNER JOIN room_types as rt ON bt.bed_type_id=rt.bed_type_id WHERE room_type=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, rt);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				BedType bt = new BedType(rs.getString(1));
				bedTypes.add(bt);
			}
			closeConnection();
		}catch(Exception e) 
		{
			
		}
		return bedTypes;
	}
	
	public static ArrayList<BedType> getAllBedType()
	{
		ArrayList<BedType> bedTypes = new ArrayList<BedType>();
	
		try {
			openConnection();
			String query="SELECT bed_type FROM bed_types;";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				BedType bt = new BedType(rs.getString(1));
				bedTypes.add(bt);
			}
			closeConnection();
		}catch(Exception e) 
		{
			
		}
		return bedTypes;
	}
	
	public static boolean insertRoomNoAndType(int roomNo,int rt)
	{
		try {
			openConnection();
			String sql = "INSERT INTO rooms(room_no,room_type_id) VALUES(?,?)";
					
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, roomNo);
			ps.setInt(2, rt);
			int line = ps.executeUpdate();
			closeConnection();
			return line>0;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	
	public static ArrayList<RoomList>getAllRoomInfo()
	{
		int No=1;
		ArrayList<RoomList> roomList = new ArrayList<RoomList>();
		try {
			openConnection();
			String query= "SELECT rooms.room_id,rooms.room_no,room_types.room_type,bed_types.bed_type,room_types.room_charges\r\n"
					+ "FROM rooms LEFT JOIN room_types ON rooms.room_type_id=room_types.room_type_id "
					+ "LEFT JOIN bed_types ON room_types.bed_type_id=bed_types.bed_type_id;\r\n"
					+ "";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				
				RoomList rl = new RoomList(No++,rs.getInt(1),rs.getInt(2),rs.getString(3),
						rs.getString(4),rs.getInt(5));
				roomList.add(rl);
			}
			closeConnection();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return roomList;
	}
	
	public static ArrayList<RoomStatus> getRoomStatus()
	{
		int no=1;
		ArrayList<RoomStatus> roomStatus = new ArrayList<RoomStatus>();
		try {
			openConnection();
			String query= "SELECT rooms.room_id,rooms.room_no,room_types.room_type\r\n"
					+ "FROM rooms LEFT JOIN room_types ON rooms.room_type_id=room_types.room_type_id";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				RoomStatus rl = new RoomStatus(no++,rs.getInt(1),rs.getInt(2),rs.getString(3));
				roomStatus.add(rl);
			}
			closeConnection();
		}catch(Exception e)
		{
			
		}
		return roomStatus;
	}
	
	public static ArrayList<RoomTypeTable>getRoomTypeTable()
	{
		int no=1;
		ArrayList<RoomTypeTable> roomTypeTable = new ArrayList<RoomTypeTable>();
		try {
			openConnection();
			String query= "SELECT room_types.room_type_id,room_types.room_type,bed_types.bed_type"
					+ ",room_types.room_charges FROM room_types "
					+ "inner JOIN bed_types ON room_types.bed_type_id=bed_types.bed_type_id;";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				RoomTypeTable rtt = new RoomTypeTable(no++,rs.getString(2),rs.getString(3),
										rs.getInt(4));
			
				roomTypeTable.add(rtt);
			}
			closeConnection();
		}catch(Exception e)
		{
			
		}
		return roomTypeTable;
	}
	
	public static boolean updateRoomNoAndRoomType(int rid,int rn,int rt)
	{
		try {
			openConnection();
			String sql= "Update rooms set room_no=?,room_type_id=? where room_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, rn);
			ps.setInt(2, rt);
			ps.setInt(3, rid);
			int line = ps.executeUpdate();
			closeConnection();
			return line>0;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean updateRoomBedType(int rtid,String rt,int rc,int bid)
	{
		try {
			openConnection();
			String sql= "Update room_types set room_type=?,room_charges=?,bed_type_id=? where room_type_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,rt);
			ps.setInt(2, rc);
			ps.setInt(3, bid);
			ps.setInt(4, rtid);
			int line = ps.executeUpdate();
			closeConnection();
			return line>0;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public static int getRoomId(int roomNo)
	{
		int rNo=0;
		try {
			openConnection();
			String query= "SELECT room_id FROM rooms where room_no=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1,roomNo);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				 rNo = rs.getInt(1);
			}
			closeConnection();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return rNo;
	}
	
	public static int getNoOfNight(String outDate,String inDate)
	{
		int rNo=1;
		try {
			openConnection();
			String query= "SELECT DATEDIFF(check_out_date,check_in_date) FROM reserve_rooms where check_out_date=?"
					+ "and check_in_date=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1,outDate);
			ps.setString(2, inDate);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				 rNo += rs.getInt(1);
			}
			closeConnection();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return rNo;
		
	}
	
	public static ArrayList<RoomTable> getRoomTable()
	{
		int no=1;
		ArrayList<RoomTable> roomTable = new ArrayList<RoomTable>();
		try {
			openConnection();
			String query= "SELECT room_id,room_no,room_type FROM rooms left JOIN room_types ON"
					+ " rooms.room_type_id=room_types.room_type_id;";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				RoomTable rt = new RoomTable(no++,rs.getInt(2),rs.getString(3));
				roomTable.add(rt);
			}
			closeConnection();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return roomTable;
	}
	
	public static boolean deleteRoom(int id)
	{
		try {
			openConnection();
			deleteRoomTypeId(id);
			String sql= "Delete from rooms where room_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			int line = ps.executeUpdate();
			closeConnection();
			return line>0;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean deleteRoomTypeId(int id)
	{
		try {
		
			String sql= "Delete from rooms where room_type_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			int line = ps.executeUpdate();
			return line>0;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public static ArrayList<AvailableRoom> getAllAvailableRoom(int rtid,String inDate,String outDate)
	{
		ArrayList<AvailableRoom> vacantRoom = new ArrayList<AvailableRoom>();
		try {
			openConnection();
			String query= "SELECT room_no FROM rooms left JOIN room_types on rooms.room_type_id=room_types.room_type_id\r\n"
					+ "WHERE room_types.room_type_id=? AND rooms.room_id NOT IN(SELECT room_id FROM reserve_rooms\r\n"
					+ "WHERE check_in_date <=? AND check_out_date >=?)\r\n";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, rtid);
			ps.setString(2, inDate);
			ps.setString(3,outDate);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				AvailableRoom room = new AvailableRoom(rs.getInt(1));
				vacantRoom.add(room);
			}
			closeConnection();
		}catch(Exception e)
		{
//			e.printStackTrace();
		}
		return vacantRoom;
	}
	
	public static boolean deleteRoomType(int rid)
	{
		try {
			openConnection();
			deleteBedType(rid);
			String sql= "Delete from room_types where rid=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, rid);
			int line = ps.executeUpdate();
			closeConnection();
			return line>0;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public static boolean deleteBedType(int bid)
	{
		try {
//			openConnection();
			String sql= "Delete from bed_types where bed_type_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, bid);
			int line = ps.executeUpdate();
//			closeConnection();
			return line>0;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public static boolean insertBedType(String type)
	{
		try {
			openConnection();
			String sql = "INSERT INTO bed_types(bed_type) VALUES(?)";
					
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, type);
			int line = ps.executeUpdate();
			closeConnection();
			return line>0;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public static int getBedTypeId(String typeName)
	{
		int bid=0;
		try {
			openConnection();
			String query="Select bed_type_id from bed_types where bed_type=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, typeName);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				bid = rs.getInt(1);
			}
			closeConnection();
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
		return bid;
	}
	
	public static int getRoomTypeId(String typeName)
	{
		int bid=0;
		try {
			openConnection();
			String query="Select room_type_id from room_types where room_type=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, typeName);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				bid = rs.getInt(1);
			}
			closeConnection();
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
		return bid;
	}
	
	public static boolean insertRoomType(int rt,int rc,int bt)
	{
		try {
			openConnection();
			String sql = "INSERT INTO room_types(room_type,room_charges,bed_type_id) VALUES(?,?,?)";
					
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, rt);
			ps.setInt(2, rc);
			ps.setInt(3, bt);
			int line = ps.executeUpdate();
			closeConnection();
			return line>0;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	
	
//	*****************End Queries For Rooms, Room Type, BedType*********************
	
	
	
	
// *****************Start Queries for show admin dashboard UI *******************
	public static int getTotalRooms()
	{
		int totalRooms =0;
		try {
			openConnection();
			String query= "SELECT COUNT(room_no) AS total FROM rooms;";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				 totalRooms = rs.getInt(1);
			}
			closeConnection();
		}catch(Exception e)
		{
			
		}
		return totalRooms;
	}
	
	public static int getTotalAdmins()
	{
		int totalAdmins =0;
		try {
			openConnection();
			String query= "SELECT count(staff_id) AS totalAdmins FROM admins where delete_date is null";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				 totalAdmins = rs.getInt(1);
			}
			closeConnection();
		}catch(Exception e)
		{
			
		}
		return totalAdmins;
	}
	
	public static int getTotalReceptionists()
	{
		int totalReceptionist =0;
		try {
			openConnection();
			String query= "SELECT count(staff_id) AS totalReceptionist FROM receptionists where delete_date is null";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				 totalReceptionist = rs.getInt(1);
			}
			closeConnection();
		}catch(Exception e)
		{
			
		}
		return totalReceptionist;
	}
	
	public static int getTotalReservations()
	{
		int totalReservations =0;
		try {
			openConnection();
			String query= "SELECT COUNT(reserve_id) AS noOfReservation FROM reservations";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				 totalReservations = rs.getInt(1);
			}
			closeConnection();
		}catch(Exception e)
		{
			
		}
		return totalReservations;
	}
	
	public static int getTotalGuests()
	{
		int totalGuests =0;
		try {
			openConnection();
			String query= "SELECT COUNT(customer_id) noOfCustomer FROM customers";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				 totalGuests = rs.getInt(1);
			}
			closeConnection();
		}catch(Exception e)
		{
			
		}
		return totalGuests;
	}
	
	public static int getTotalRoomType()
	{
		int roomTypes =0;
		try {
			openConnection();
			String query= "SELECT COUNT(room_type_id) FROM room_types";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				 roomTypes = rs.getInt(1);
			}
			closeConnection();
		}catch(Exception e)
		{
			
		}
		return roomTypes;
	}

	public static int getRoomCharges(int rt)
	{
		int id=-1;
		try {
			openConnection();
			String sql="SELECT room_charges FROM room_types WHERE room_type_id=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, rt);

			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				id = rs.getInt(1);
			}
		}catch(Exception e)
		{
			
		}
		closeConnection();
		return id;
	}
	
	public static boolean deleteRoomNoAndType(int id)
	{
		try {
			openConnection();
			String sql= "Delete from rooms where room_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			int line = ps.executeUpdate();
			closeConnection();
			return line>0;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public static boolean deleteRoomTypeChargesBedType(int id)
	{

		try {
			openConnection();
//			deleteBedTypeId(id);
			String sql= "Delete from room_type where room_type_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			int line = ps.executeUpdate();
			closeConnection();
			return line>0;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public static boolean deleteBedTypeId(int id)
	{
		
		try {
//			openConnection();
			String sql= "Delete from room_type where bed_type_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			int line = ps.executeUpdate();
//			closeConnection();
			return line>0;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	
	public static boolean insertRoomTypeBedType(String rt,int rc,int bt)
	{
		try {
			openConnection();
			String sql = "INSERT INTO room_types(room_type,room_charges,bed_type_id)VALUES(?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, rt);
			ps.setInt(2, rc);
			ps.setInt(3,bt);
			int line = ps.executeUpdate();
			closeConnection();
			return line>0;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	
//	***************End Queries For show Admin dashboard UI***************************
	
	
	
	
//	**************Start Queries For Reservation And Check IN**********************
	

	public static ArrayList<CheckIn> getAllCheckInLists()
	{
		ArrayList<CheckIn> checkin = new ArrayList<CheckIn>();
		try {
			openConnection();
			String query= "SELECT re.reserve_id,check_in_id,c.name,c.phone,reserve_date,check_in_date,check_in_time,\r\n"
					+ "check_out_date,check_out_time,DATEDIFF(check_out_date,check_in_date) as no_of_nights,\r\n"
					+ "room_no,room_type,bed_type,room_charges,deposit,reserve_type,receptionists.name\r\n"
					+ "FROM customers c\r\n"
					+ "inner JOIN reservations re ON c.customer_id=re.customer_id\r\n"
					+ "inner JOIN reservation_types rt ON re.reserve_type_id= rt.reserve_type_id\r\n"
					+ "inner JOIN reserve_rooms rr ON re.reserve_id=rr.reserve_id\r\n"
					+ "inner JOIN check_in_out cio ON rr.reserve_room_id=cio.reserve_room_id\r\n"
					+ "inner JOIN rooms r ON rr.room_id=r.room_id\r\n"
					+ "inner JOIN room_types rot ON r.room_type_id=rot.room_type_id\r\n"
					+ "inner JOIN bed_types bt ON rot.bed_type_id=bt.bed_type_id\r\n"
					+ "INNER JOIN receptionists ON re.receptionist_id= receptionists.receptionist_id\r\n"
					+ "WHERE check_out IS NULL\r\n"
					+ "";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				CheckIn ci = new CheckIn(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),
						rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),
						rs.getInt(10),rs.getInt(11),rs.getString(12),rs.getString(13),rs.getInt(14),
						rs.getInt(15),rs.getString(16),rs.getString(17));
;
				checkin.add(ci);
			}
			closeConnection();
		}catch(Exception e)
		{
			
		}
		return checkin;
	}
	
	
	public static ArrayList<CheckIn> getCurrentCheckInLists()
	{
		ArrayList<CheckIn> checkin = new ArrayList<CheckIn>();
		try {
			openConnection();
			String query= "SELECT re.reserve_id,check_in_id,c.name,c.phone,reserve_date,check_in_date,check_in_time,\r\n"
					+ "check_out_date,check_out_time,DATEDIFF(check_out_date,check_in_date) as no_of_nights,\r\n"
					+ "room_no,room_type,bed_type,room_charges,deposit,reserve_type,receptionists.name\r\n"
					+ "FROM customers c\r\n"
					+ "inner JOIN reservations re ON c.customer_id=re.customer_id\r\n"
					+ "inner JOIN reservation_types rt ON re.reserve_type_id= rt.reserve_type_id\r\n"
					+ "inner JOIN reserve_rooms rr ON re.reserve_id=rr.reserve_id\r\n"
					+ "inner JOIN check_in_out cio ON rr.reserve_room_id=cio.reserve_room_id\r\n"
					+ "inner JOIN rooms r ON rr.room_id=r.room_id\r\n"
					+ "inner JOIN room_types rot ON r.room_type_id=rot.room_type_id\r\n"
					+ "inner JOIN bed_types bt ON rot.bed_type_id=bt.bed_type_id\r\n"
					+ "INNER JOIN receptionists ON re.receptionist_id= receptionists.receptionist_id\r\n"
					+ "WHERE check_out IS NULL AND check_in_date = CURDATE()"
					+ "";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				CheckIn ci = new CheckIn(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),
						rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),
						rs.getInt(10),rs.getInt(11),rs.getString(12),rs.getString(13),rs.getInt(14),
						rs.getInt(15),rs.getString(16),rs.getString(17));
;
				checkin.add(ci);
			}
			closeConnection();
		}catch(Exception e)
		{
			
		}
		return checkin;
	}
	
	
	public static boolean insertCheckIn(int rId,int gId,String checkIn)
	{

		try {
			openConnection();
			String sql = "INSERT INTO check_in_out(reserve_room_id,guest_id,check_in_time) VALUES(?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,rId);
			ps.setInt(2,gId);
			ps.setString(3, checkIn);
			int line = ps.executeUpdate();
			closeConnection();
			return line>0;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean deleteReservation(int id)
	{
		try {
			openConnection();

			String sql= "Delete from reservations where reservation_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			int line = ps.executeUpdate();
			closeConnection();
			return line>0;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public static boolean deleteGuestCheckIn(int id)
	{
		try {
//			openConnection();
			String sql= "Delete from check_in where check_in_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			int line = ps.executeUpdate();
//			closeConnection();
			return line>0;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public static boolean insertReserveType(int type)
	{
		try {
			openConnection();
			String sql = "INSERT INTO reservation_types(reserve_type) VALUES(?)";
					
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,type);
			int line = ps.executeUpdate();
			closeConnection();
			return line>0;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public static ArrayList<Reservation> getAllReservationLists()
	{
		int no=1;
		ArrayList<Reservation> reserve = new ArrayList<Reservation>();
		try {
			openConnection();
			String query= "SELECT re.reserve_id,c.name,c.phone,reserve_date,check_in_date,check_out_date,\r\n"
					+ "DATEDIFF(check_out_date,check_in_date) as no_of_nights,room_no,\r\n"
					+ "room_type,bed_type,room_charges,deposit,reserve_type,rr.reserve_room_id\r\n"
					+ "FROM customers c\r\n"
					+ "left JOIN reservations re ON c.customer_id=re.customer_id\r\n"
					+ "left JOIN reservation_types rt ON re.reserve_type_id= rt.reserve_type_id\r\n"
					+ "left JOIN reserve_rooms rr ON re.reserve_id=rr.reserve_id\r\n"
					+ "left JOIN rooms r ON rr.room_id=r.room_id\r\n"
					+ "left JOIN room_types rot ON r.room_type_id=rot.room_type_id\r\n"
					+ "left JOIN bed_types bt ON rot.bed_type_id=bt.bed_type_id\r\n"
					+ "where reserve_to_check_in is NULL";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Reservation re = new Reservation(no++,rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4),
								rs.getDate(5),rs.getDate(6),rs.getInt(7),rs.getString(8),rs.getString(9),rs.getString(10),
								rs.getInt(11),rs.getInt(12),rs.getString(13),rs.getInt(14));
						reserve.add(re);
			}
			closeConnection();
		}catch(Exception e)
		{
			
		}
		return reserve;
	}
	
	public static boolean addReserveToCheckInDate(int rrid)
	{
		try {
			openConnection();
			String sql = "Update reserve_rooms set reserve_to_check_in=curdate() where reserve_room_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, rrid);
			int line = ps.executeUpdate();
			closeConnection();
			return line>0;
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public static ArrayList<ReserveType> getReservationType()
	{
		ArrayList<ReserveType> reserveTypes = new ArrayList<ReserveType>();
		
	
		try {
			openConnection();
			String query="Select reserve_type_id,reserve_type from reservation_types";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				ReserveType rt = new ReserveType(rs.getInt(1),rs.getString(2));
				reserveTypes.add(rt);
			}
			closeConnection();
		}catch(Exception e) 
		{
			
		}
		return reserveTypes;
	}
	
	public static ArrayList<Reservation> getReserveDataToCheckIn(int id)
	{
		int no=1;
		ArrayList<Reservation> reserve = new ArrayList<Reservation>();
		try {
			openConnection();
			String query= "SELECT re.reserve_id,check_in_id,c.name,c.phone,reserve_date,check_in_date,\r\n"
					+ "check_out_date,DATEDIFF(check_out_date,check_in_date) as no_of_nights,room_no,\r\n"
					+ "room_type,bed_type,room_charges,deposit,reserve_type,rr.reserve_room_id\r\n"
					+ "FROM customers c\r\n"
					+ "left JOIN reservations re ON c.customer_id=re.customer_id\r\n"
					+ "left JOIN reservation_types rt ON re.reserve_type_id= rt.reserve_type_id\r\n"
					+ "left JOIN reserve_rooms rr ON re.reserve_id=rr.reserve_id\r\n"
					+ "LEFT JOIN check_in_out cio ON rr.reserve_room_id=cio.reserve_room_id\r\n"
					+ "left JOIN rooms r ON rr.room_id=r.room_id\r\n"
					+ "left JOIN room_types rot ON r.room_type_id=rot.room_type_id\r\n"
					+ "left JOIN bed_types bt ON rot.bed_type_id=bt.bed_type_id \r\n"
					+ "WHERE re.reserve_id=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Reservation re = new Reservation(no++,rs.getInt(1),rs.getString(2),rs.getString(3), rs.getDate(4),rs.getDate(5), rs.getDate(6),
						rs.getInt(7),rs.getString(8), rs.getString(9), rs.getString(10), rs.getInt(11),rs.getInt(12), rs.getString(13), rs.getInt(14)
						);
				reserve.add(re);
			}
			closeConnection();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return reserve;
	}
	
	public static boolean insertReservation(String resDate,int deposit,int reTypeId,int receptionistId,int cId)
	{
		
		try {
			openConnection();
			String sql = "INSERT INTO reservations(reserve_date,deposit,reserve_type_id,receptionist_id,customer_id) VALUES(?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
		
			ps.setString(1,resDate);
			ps.setInt(2,deposit);
			ps.setInt(3, reTypeId);
			ps.setInt(4, receptionistId);  
			ps.setInt(5, cId);
			int line = ps.executeUpdate();
			closeConnection();
			return line>0;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean insertReserveRooms(int resId,int roomId,String inDate,String outDate)
	{
		try {
			openConnection();
			String sql = "INSERT INTO reserve_rooms(reserve_id,room_id,check_in_date,check_out_date) VALUES(?,?,?,?)";
					
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, resId);
			ps.setInt(2, roomId);
			ps.setString(3, inDate);
			ps.setString(4,outDate);
			int line = ps.executeUpdate();
			closeConnection();
			return line>0;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public static int getReserveTypeId(String type)
	{
		int ans=0;
		
		try {
			openConnection();
			String query="Select reserve_type_id from reservation_types where reserve_type=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, type);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next())
			{
				ans = rs.getInt(1);
			}
				closeConnection();
				return ans;
			}catch(Exception e) 
			{
				e.printStackTrace();
			}
			return ans;
	}

	public static boolean insertGuestNameAndPhone(String n,String ph)
	{
		try {
			openConnection();
			String sql = "INSERT INTO guests(name,phone) VALUES(?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,n);
			ps.setString(2,ph);
			int line = ps.executeUpdate();
			closeConnection();
			return line>0;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	

	
	//	**************End Queries For Reservation *****************************
	

	
//	***********Start Queries For Check Out***********************
	public static boolean addCheckOutList(int cid)
	{
		try {
			openConnection();
			String sql = "Update check_in_out set check_out=curdate() where check_in_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, cid);
			int line = ps.executeUpdate();
			closeConnection();
			return line>0;
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean addCheckOutTime(int id,String outTime)
	{
		try {
			openConnection();
			String sql = "Update check_in_out set check_out_time=? where check_in_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, outTime);
			ps.setInt(2, id);
			int line = ps.executeUpdate();
			closeConnection();
			return line>0;
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public static ArrayList<CheckOutData> getCheckOutData(int rno)
	{
		ArrayList<CheckOutData> checkOut = new ArrayList<CheckOutData>();
		try {
			openConnection();
			String query= "SELECT ci.check_in_id,g.name,g.phone,\r\n"
					+ "ro.room_no,rt.room_type,bt.bed_type,\r\n"
					+ "rr.check_in_date,rr.check_out_date,rt.room_charges,\r\n"
					+ "DATEDIFF(rr.check_out_date,rr.check_in_date) AS NoOfNight,\r\n"
					+ "(DATEDIFF(rr.check_out_date,rr.check_in_date)*rt.room_charges) AS TotalAmount,\r\n"
					+ "r.deposit,p.payment_amount,\r\n"
					+ "(DATEDIFF(rr.check_out_date,rr.check_in_date)*rt.room_charges)-r.deposit AS credits,\r\n"
					+ "rr.reserve_room_id\r\n"
					+ "from reservations r\r\n"
					+ "inner JOIN reserve_rooms rr ON r.reserve_id=rr.reserve_id\r\n"
					+ "inner JOIN rooms ro ON rr.room_id=ro.room_id\r\n"
					+ "inner JOIN room_types rt ON ro.room_type_id=rt.room_type_id\r\n"
					+ "inner JOIN bed_types bt ON rt.bed_type_id=bt.bed_type_id\r\n"
					+ "inner JOIN check_in_out ci ON ci.reserve_room_id=rr.reserve_room_id\r\n"
					+ "inner JOIN guests g ON ci.guest_id=g.guest_id\r\n"
					+ "left JOIN payments p ON rr.reserve_room_id=p.reserve_room_id\r\n"
					+ "left JOIN payment_types pt ON p.payment_type_id=pt.payment_type_id\r\n"
					+ "WHERE room_no=? AND ci.check_out IS NULL";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, rno);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				CheckOutData co = new CheckOutData(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),
						rs.getString(6),rs.getString(7),rs.getString(8),rs.getInt(9),rs.getInt(10),
						rs.getInt(11),rs.getInt(12),rs.getInt(13),rs.getInt(14),rs.getInt(15));
				checkOut.add(co);
			}
			closeConnection();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return checkOut;
	}
	
	public static ArrayList<CheckOut> getCheckOutListTable()
	{
		int no=1;
		ArrayList<CheckOut> checkout = new ArrayList<CheckOut>();
		try {
			openConnection();
			String query= "SELECT re.reserve_id,check_in_id,c.name,c.phone,reserve_date,check_in_date,check_in_time,\r\n"
					+ "check_out_date,check_out_time,DATEDIFF(check_out_date,check_in_date) as no_of_nights,\r\n"
					+ "room_no,room_type,bed_type,room_charges,deposit,reserve_type,receptionists.name\r\n"
					+ "FROM customers c\r\n"
					+ "inner JOIN reservations re ON c.customer_id=re.customer_id\r\n"
					+ "inner JOIN reservation_types rt ON re.reserve_type_id= rt.reserve_type_id\r\n"
					+ "inner JOIN reserve_rooms rr ON re.reserve_id=rr.reserve_id\r\n"
					+ "inner JOIN check_in_out cio ON rr.reserve_room_id=cio.reserve_room_id\r\n"
					+ "inner JOIN rooms r ON rr.room_id=r.room_id\r\n"
					+ "inner JOIN room_types rot ON r.room_type_id=rot.room_type_id\r\n"
					+ "inner JOIN bed_types bt ON rot.bed_type_id=bt.bed_type_id\r\n"
					+ "INNER JOIN receptionists ON re.receptionist_id= receptionists.receptionist_id\r\n"
					+ "WHERE check_out IS NOt NULL";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				CheckOut co = new CheckOut(no++,rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),
						rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),
						rs.getInt(10),rs.getInt(11),rs.getString(12),rs.getString(13),rs.getInt(14),
						rs.getInt(15),rs.getString(16),rs.getString(17));
;
				checkout.add(co);
			}
			closeConnection();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return checkout;
	}
	
	public static boolean updateCheckOutDate(String co,int id)
	{
		try {
			openConnection();
			String sql = "Update reserve_rooms set check_out_date=? where reserve_room_id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, co);
			ps.setInt(2, id);
			int line = ps.executeUpdate();
			closeConnection();
			return line>0;
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	
	public static ArrayList<CheckOut> getCurrentCheckOutListTable()
	{
		int no=1;
		ArrayList<CheckOut> checkout = new ArrayList<CheckOut>();
		try {
			openConnection();
			String query= "SELECT re.reserve_id,check_in_id,c.name,c.phone,reserve_date,check_in_date,check_in_time,\r\n"
					+ "check_out_date,check_out_time,DATEDIFF(check_out_date,check_in_date) as no_of_nights,\r\n"
					+ "room_no,room_type,bed_type,room_charges,deposit,reserve_type,receptionists.name\r\n"
					+ "FROM customers c\r\n"
					+ "inner JOIN reservations re ON c.customer_id=re.customer_id\r\n"
					+ "inner JOIN reservation_types rt ON re.reserve_type_id= rt.reserve_type_id\r\n"
					+ "inner JOIN reserve_rooms rr ON re.reserve_id=rr.reserve_id\r\n"
					+ "inner JOIN check_in_out cio ON rr.reserve_room_id=cio.reserve_room_id\r\n"
					+ "inner JOIN rooms r ON rr.room_id=r.room_id\r\n"
					+ "inner JOIN room_types rot ON r.room_type_id=rot.room_type_id\r\n"
					+ "inner JOIN bed_types bt ON rot.bed_type_id=bt.bed_type_id\r\n"
					+ "INNER JOIN receptionists ON re.receptionist_id= receptionists.receptionist_id\r\n"
					+ "WHERE check_out IS NOt NULL AND check_out_date = CURDATE()";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				CheckOut co = new CheckOut(no++,rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),
						rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),
						rs.getInt(10),rs.getInt(11),rs.getString(12),rs.getString(13),rs.getInt(14),
						rs.getInt(15),rs.getString(16),rs.getString(17));
;
				checkout.add(co);
			}
			closeConnection();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return checkout;
	}
	
//	****************End Queries For Check Out***************************
	
	
	
//	**********Start Queries For Report *******************************
	
	public static Number getIncomeByMonth(int m,int y)
	{
		Number income=0;
		try {
			openConnection();
			String query= "SELECT SUM(payment_amount+deposit),MONTH(payment_date),YEAR(payment_date)\r\n"
					+ "FROM reserve_rooms\r\n"
					+ "left JOIN payments ON reserve_rooms.reserve_room_id=payments.reserve_room_id\r\n"
					+ "left JOIN payment_types ON payments.payment_type_id=payment_types.payment_type_id\r\n"
					+ "left JOIN reservations on reserve_rooms.reserve_id=reservations.reserve_id\r\n"
					+ "GROUP BY MONTH(payment_date),YEAR(payment_date)";
			PreparedStatement ps = con.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				income = rs.getInt(1);
			}
			closeConnection();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return income;
	}

	public static ArrayList<Integer> getIncomeReport(int year)
	{
		ArrayList<Integer> income = new ArrayList<Integer>();
		try {
			openConnection();
			String query= "SELECT SUM(payment_amount+deposit) AS totalIncome,MONTHNAME(payment_date) AS month,YEAR(payment_date) AS year\r\n"
					+ "FROM reserve_rooms\r\n"
					+ "left JOIN payments ON reserve_rooms.reserve_room_id=payments.reserve_room_id\r\n"
					+ "left JOIN payment_types ON payments.payment_type_id=payment_types.payment_type_id\r\n"
					+ "left JOIN reservations on reserve_rooms.reserve_id=reservations.reserve_id\r\n"
					+ "WHERE YEAR(payment_date)=?\r\n"
					+ "GROUP BY MONTHNAME(payment_date),YEAR(payment_date)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, year);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Integer in = rs.getInt(1);
				income.add(in);
			}
			closeConnection();
		}catch(Exception e)
		{
			
		}
		return income;
	}
	
	public static ArrayList<Integer> getRoomReport(Date start,Date end)
	{
		ArrayList<Integer> roomReport = new ArrayList<Integer>();
		try {
			openConnection();
			String query= "SELECT COUNT(*) noOfRoomOccupied,MONTH(check_in_date) AS MONTH ,YEAR(check_in_date) AS YEAR  FROM reserve_rooms\r\n"
					+ "WHERE  check_in_date>=? and check_in_date<=? \r\n"
					+ "GROUP BY MONTH(check_in_date),YEAR(check_in_date)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setDate(1, start);
			ps.setDate(2, end);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Integer room = rs.getInt(1);
				roomReport.add(room);
				
			}
			closeConnection();
		}catch(Exception e)
		{
			
		}
		return roomReport;
	}
	
	
	public static ArrayList<Integer> getRoomReport(int y)
	{
		ArrayList<Integer> roomReport = new ArrayList<Integer>();
		try {
			openConnection();
			String query= "SELECT COUNT(*) noOfRoomOccupied,MONTHNAME(check_in_date) AS MONTH ,YEAR(check_in_date) AS YEAR  FROM reserve_rooms\r\n"
					+ "WHERE  Year(check_in_date)=? \r\n"
					+ "GROUP BY MONTHNAME(check_in_date),YEAR(check_in_date)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, y);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Integer room = rs.getInt(1);
				roomReport.add(room);
				
			}
			closeConnection();
		}catch(Exception e)
		{
			
		}
		return roomReport;
	}
	
	public static ArrayList<IncomeReport> getIncomeReportTable()
	{
		ArrayList<IncomeReport> income = new ArrayList<IncomeReport>();
		try {
			openConnection();
			String query= "SELECT SUM(payment_amount+deposit) AS totalIncome,MONTHNAME(payment_date) AS month,YEAR(payment_date) AS year\r\n"
					+ "FROM reserve_rooms\r\n"
					+ "left JOIN payments ON reserve_rooms.reserve_room_id=payments.reserve_room_id\r\n"
					+ "left JOIN payment_types ON payments.payment_type_id=payment_types.payment_type_id\r\n"
					+ "left JOIN reservations on reserve_rooms.reserve_id=reservations.reserve_id\r\n"
					+ "WHERE YEAR(payment_date)\r\n"
					+ "GROUP BY MONTH(payment_date),YEAR(payment_date)";
			PreparedStatement ps = con.prepareStatement(query);
//			ps.setInt(1, year);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				IncomeReport ir = new IncomeReport(rs.getInt(1),rs.getString(2),rs.getInt(3));
				income.add(ir);
			}
			closeConnection();
		}catch(Exception e)
		{
			
		}
		return income;
	}
	
	public static ArrayList<RoomOccupiedReport> getRoomOccupiedData(int y)
	{
		ArrayList<RoomOccupiedReport> roomReport = new ArrayList<RoomOccupiedReport>();
		try {
			openConnection();
			String query= "SELECT COUNT(*) noOfRoomOccupied,MONTHNAME(check_in_date) AS MONTH ,YEAR(check_in_date) AS YEAR  FROM reserve_rooms\r\n"
					+ "WHERE  YEAR(check_in_date)=?\r\n"
					+ "GROUP BY MONTH(check_in_date),YEAR(check_in_date)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, y);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				RoomOccupiedReport room = new RoomOccupiedReport(rs.getInt(1),rs.getString(2),rs.getInt(3));
				roomReport.add(room);
				
			}
			closeConnection();
		}catch(Exception e)
		{
			
		}
		return roomReport;
	}
	
	public static ArrayList<PaymentReport> getPaymentByType()
	{
		ArrayList<PaymentReport> payReport = new ArrayList<PaymentReport>();
		try {
			openConnection();
			String query= "SELECT payment_type,sum(payment_amount) FROM payment_types\r\n"
					+ "inner JOIN payments ON payment_types.payment_type_id=payments.payment_type_id\r\n"
					+ "WHERE YEAR(payment_date)\r\n"
					+ "GROUP BY payment_type";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				PaymentReport pay = new PaymentReport(rs.getString(1),rs.getInt(2));
				payReport.add(pay);
				
			}
			closeConnection();
		}catch(Exception e)
		{
			
		}
		return payReport;
	}
	
	
	public static ArrayList<PaymentReport> getPaymentByType(String start,String end)
	{
		ArrayList<PaymentReport> payReport = new ArrayList<PaymentReport>();
		try {
			openConnection();
			String query= "SELECT payment_type,sum(payment_amount) FROM payment_types\r\n"
					+ "inner JOIN payments ON payment_types.payment_type_id=payments.payment_type_id\r\n"
					+ "WHERE payment_date between ? And ? \r\n"
					+ "GROUP BY payment_type";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, start);
			ps.setString(2, end);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				PaymentReport pay = new PaymentReport(rs.getString(1),rs.getInt(2));
				payReport.add(pay);
				
			}
			closeConnection();
		}catch(Exception e)
		{
			
		}
		return payReport;
	}
	
	
	
//	***********End Queries For Report ************************************
	
	
	public static void main(String args[])
	{

//		ArrayList<PaymentReport> al = getPaymentByType();
//		for(PaymentReport pr:al)
//		{
//			System.out.println(pr);
//		}
		
//		ArrayList<RoomList> al = getAllRoomInfo();
//		for(RoomList rl:al)
//		{
//			System.out.println(rl);
//		}
		
//		ArrayList<GuestProfile> al = getAllGuestProfile();
//		for(GuestProfile gp:al)
//		{
//			System.out.println(gp);
//		}
		
//		ArrayList<String> al = getReceptionistStaffId();
//		for(String s:al)
//		{
//			System.out.println(s);
//		}
//		
//		ArrayList<Admin> al = getAllAdmins();
//		for(Admin a:al)
//		{
//			System.out.println(a.getStaffId());
//			System.out.println(a.getPhone());
//			System.out.println(a.getNrc());
//		}
		
//		int n = getNoOfNight("2021-09-07","2021-09-07");
//		System.out.println(n);
//		
//		}
	}
}
