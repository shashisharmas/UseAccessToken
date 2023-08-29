package com.amdocs.propertySearch.main;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

import com.amdocs.propertySearch.dao.PropertyDAO;
import com.amdocs.propertySearch.exception.PropertyNotFoundException;
import com.amdocs.propertySearch.model.Property;



public class Main {
	public static void main(String args[]) throws IOException, PropertyNotFoundException {
		Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PropertyDAO pd = new PropertyDAO();
		int choice;
		do {
			System.out.println("1. Add new property");
			System.out.println("2. Update Property Cost");
			System.out.println("3. Delete Property");
			System.out.println("4. Find By City");
			System.out.println("5. View all properties");
			System.out.println("6. Find by cost");
			System.out.println("7. Find by no of rooms and city");
			System.out.println("8. Exit");
			
			choice = sc.nextInt();
			switch(choice) {
				case 1:
					try {
						
					
					Property p = new Property();
					System.out.println("Enter Property ID: ");
					p.setPropertyId(Integer.parseInt(br.readLine()));
					System.out.println("Enter no of rooms: ");
					p.setNoOfRooms((br.readLine()).toUpperCase());
					System.out.println("Enter area in sqft: ");
					p.setAreaInSqft(Integer.parseInt(br.readLine()));
					
					System.out.println("floor no: ");
					p.setFloorNo(Integer.parseInt(br.readLine()));
					System.out.println("City: ");
					p.setCity(br.readLine());
					
					System.out.println("state: ");
					p.setState(br.readLine());
					
					System.out.println("cost: ");
					p.setCost(Double.parseDouble(br.readLine()));
					
					
					System.out.println("owner name: ");
					p.setOwnerName(br.readLine());
					System.out.println("owner contact number: ");
					p.setOwnerContactNo(br.readLine());
					
					int rows=pd.addProperty(p);
					System.out.println(rows+" row inserted.");
					break;}
					catch(Exception e) {
						e.printStackTrace();
					}
				case 2:
					System.out.println("Enter property Id");
					int id=Integer.parseInt(br.readLine());
					System.out.println("Enter new cost");
					double newCost=Double.parseDouble(br.readLine());
					boolean flag=pd.updatePropertyCost(id,newCost);
					System.out.println(flag);
					break;
				case 3:
					System.out.println("Enter Property Id");
					int id1=Integer.parseInt(br.readLine());
					int rows1=pd.deleteProperty(id1);
					break;
				case 4:
					System.out.println("enter city name");
					String city=br.readLine();
					List<Property> ls=pd.searchByCity(city);
					if(ls.size()==0) {
						try {
							throw new PropertyNotFoundException();
						}catch(Exception e) {
							continue;
						}
						
					}else {
						for(Property p1:ls) {
							System.out.println(p1);
						}
					}
					break;
				case 5:
					List<Property> ps=pd.showAllProperties();
					for(Property p1:ps) {
						System.out.println(p1);
					}
					break;
				case 6:
					System.out.println("enter starting cost");
					double startingCost=Double.parseDouble(br.readLine());
					System.out.println("enter maximum cost");
					double maximumCost=Double.parseDouble(br.readLine());
					List<Property> lsRange=pd.searchByCost(startingCost,maximumCost);
					if(lsRange.size()==0) {
						try {
							throw new PropertyNotFoundException();
						}catch(Exception e) {
							continue;
						}
						
					}else {
						for(Property p1:lsRange) {
							System.out.println(p1);
						}
					}
					break;
				case 7:
					System.out.println("enter no of rooms(1BHK/2BHK)");
					String rooms="";
					rooms=(br.readLine().toUpperCase());
					try {
						
						String temp=rooms.substring(rooms.length() - 3);
						
					}catch(Exception e) {
						System.out.println("enter no of rooms(1BHK/2BHK) in correct format.");
						continue;
					}
					
					
					System.out.println("enter city");
					String city2=br.readLine();
					
					List<Property> lsCityAndRooms=pd.searchByNoOfRoomsAndCity(rooms,city2);
					if(lsCityAndRooms.size()==0) {
						try {
						throw new PropertyNotFoundException();
						}catch(Exception e) {
							continue;
						}
					}else {
						for(Property p1:lsCityAndRooms) {
							System.out.println(p1);
						}
					}
					break;
				
			}
		}while(choice != 8);
	}
}
