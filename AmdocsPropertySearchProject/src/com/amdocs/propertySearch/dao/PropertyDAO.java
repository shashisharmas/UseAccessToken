package com.amdocs.propertySearch.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.amdocs.propertySearch.model.Property;



public class PropertyDAO {
	//private List<Property> properties = new ArrayList<Property>();
	DBConnection dbObj = new DBConnection();
	Connection c=dbObj.getConnection();

	public int addProperty(Property p) {
		// TODO Auto-generated method stub
		String q="insert into properties2 values(?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = c.prepareStatement(q);
			ps.setInt(1, p.getPropertyId());
			ps.setString(2, p.getNoOfRooms());
			ps.setDouble(3, p.getAreaInSqft());
			ps.setInt(4, p.getFloorNo());
			ps.setString(5, p.getCity());
			ps.setString(6, p.getState());
			ps.setDouble(7, p.getCost());
			ps.setString(8, p.getOwnerName());
			ps.setString(9, p.getOwnerContactNo());
			
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public int deleteProperty(int propertyId) {
		// TODO Auto-generated method stub
		DBConnection dbObj = new DBConnection();
		Connection c=dbObj.getConnection();
		String q = "delete from properties2 where propertyId =?";
		try {
			PreparedStatement ps=c.prepareStatement(q);
			ps.setInt(1, propertyId);
			int count=ps.executeUpdate();
			System.out.println("deletion code executed");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public List<Property> showAllProperties() {
		// TODO Auto-generated method stub
		DBConnection dbObj = new DBConnection();
		Connection c=dbObj.getConnection();
		List<Property> properties = new ArrayList<>();
		String q="select * from properties2";
		try {
			Statement st=c.createStatement();
			ResultSet rs = st.executeQuery(q);
			while(rs.next()){
				properties.add(getPropertyFromResultSet(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return properties;
	}

	private Property getPropertyFromResultSet(ResultSet rs) {
		// TODO Auto-generated method stub
		Property property = new Property();
		try {
			property.setPropertyId(rs.getInt("propertyId"));
			property.setNoOfRooms(rs.getString("noOfRooms"));
			property.setAreaInSqft(rs.getDouble("areaInSqft"));
			property.setFloorNo(rs.getInt("floorNo"));
			property.setCity(rs.getString("city"));
			property.setState(rs.getString("state"));
			property.setCost(rs.getDouble("cost"));
			property.setOwnerName(rs.getString("ownerName"));
			property.setOwnerContactNo(rs.getString("ownerContactNo"));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return property;
	}

	public boolean updatePropertyCost(int propertyId, double cost) {
		DBConnection dbObj = new DBConnection();
		Connection c=dbObj.getConnection();
		String q = "update  properties2 set cost=? where propertyId =?";
		try {
			PreparedStatement ps=c.prepareStatement(q);
			ps.setDouble(1, cost);
			ps.setInt(2, propertyId );
			int count=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
		
	}

	public List<Property> searchByCity(String city) {
		// TODO Auto-generated method stub
		DBConnection dbObj = new DBConnection();
		Connection c=dbObj.getConnection();
		List<Property> properties = new ArrayList<>();
		String q="select * from properties2 where city=?";
		try {
			PreparedStatement ps=c.prepareStatement(q);
			ps.setString(1, city);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				properties.add(getPropertyFromResultSet(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return properties;
	}

	public List<Property> searchByCost(double startingCost, double maximumCost) {
		// TODO Auto-generated method stub
		DBConnection dbObj = new DBConnection();
		Connection c=dbObj.getConnection();
		List<Property> properties = new ArrayList<>();
		String q="select * from properties2 where cost betweem ? and ?";
		try {
			PreparedStatement ps=c.prepareStatement(q);
			ps.setDouble(1, startingCost);
			ps.setDouble(2, maximumCost);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				properties.add(getPropertyFromResultSet(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return properties;
		
	}

	public List<Property> searchByNoOfRoomsAndCity(String rooms, String city2) {
		// TODO Auto-generated method stub
		DBConnection dbObj = new DBConnection();
		Connection c=dbObj.getConnection();
		List<Property> properties = new ArrayList<>();
		String q="select * from properties2 where city = ? and noOfRooms=?";
		try {
			PreparedStatement ps=c.prepareStatement(q);
			ps.setString(1, city2);
			ps.setString(2, rooms);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				properties.add(getPropertyFromResultSet(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return properties;
	}
}

