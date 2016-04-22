package com.spoj.practice;

import java.util.*;

public class SOCNETC {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int noOfUsers = in.nextInt();
		int maxCommunitySize = in.nextInt();
		Network network = new Network(noOfUsers, maxCommunitySize);
		int queries = in.nextInt();
		
		for(int i=1; i<=queries; i++) {
			String queryType = in.next();
			int user1 = in.nextInt();
			int user2;
			switch (queryType) {
			case "A":
				user2 = in.nextInt();
				network.requestSent(user1, user2);				
				break;
			
			case "E":
				user2 = in.nextInt();
				if(network.areInSameCommunity(user1,user2)) {
					System.out.println("Yes");
				} else {
					System.out.println("No");					
				}
				break;
			case "S":
				System.out.println(network.sizeOfCommunity(user1));
				break;
			default:
				break;
			}
		}
		in.close();
	}

}

class Network {
	
	int noOfUsers;
	private static int maxCommunitySize;
//	Map<Integer, Community> userCommunityMap;
	Map<Integer, User> userCommunityMap;
	
	public Network(int noOfUsers, int maxCommunitySize) {
		this.noOfUsers = noOfUsers;
		Network.maxCommunitySize = maxCommunitySize;
//		userCommunityMap = new HashMap<Integer, Community>();
		userCommunityMap = new HashMap<Integer, User>();
		for(int it=1; it<=noOfUsers; it++) {
//			userCommunityMap.put(Integer.valueOf(it), new Community(it));
			userCommunityMap.put(Integer.valueOf(it), new User(it));
		}
	}
	
	public void requestSent(int requester, int requestee) {
		Community community1 = userCommunityMap.get(Integer.valueOf(requester)).community;
		Community community2 = userCommunityMap.get(Integer.valueOf(requestee)).community;
		if(community1.getSize() + community2.getSize() <= Network.maxCommunitySize ) {
			Community mergedCommunity = mergeTwoCommunities(community1, community2);
			updateMergedCommunityMembers(mergedCommunity);
		}
	}
	
	public boolean areInSameCommunity(int user1, int user2) {
		Community community1 = userCommunityMap.get(Integer.valueOf(user1)).community;
		Community community2 = userCommunityMap.get(Integer.valueOf(user2)).community;
		return community1.equals(community2);
	}
	
	public Community mergeTwoCommunities(Community community1, Community community2) {
		for(Iterator<Integer> it=community2.members.iterator(); it.hasNext();) {
			community1.addMember(it.next());
		}
		return community1;
	}
	
	public void updateMergedCommunityMembers(Community community) {
		for(Iterator<Integer> it=community.members.iterator(); it.hasNext();) {
			userCommunityMap.put(it.next(), new User(community));
		}
	}
	
	public int sizeOfCommunity(int user) {
		return userCommunityMap.get(Integer.valueOf(user)).community.getSize();
	}
}

class User {
	
	int name;
	Community community;
	
	public User(int name) {
		this.name = name;
		community = new Community(name);
	}
	
	public User(Community community) {
		this.community = community;
	}
}

class Community {
	
//	private String name;
//	SetOfIntegerSyntax members;
//	Collections members ;
//	Set<Integer> members = new 
	public TreeSet<Integer> members;
	
	public Community(int member) {
//		name = String.valueOf(member);
//		members = new SetOfIntegerSyntax(member) {
//		};
		members = new TreeSet<>();
		members.add(Integer.valueOf(member));
	}
	
//	public void rename(String newName) {
//		name = newName;
//	}
	
//	public String getName() {
//		 return name;
//	}
	
	public int getSize() {
		return members.size();
	}
	
	public void addMember(int newMember) {
		members.add(Integer.valueOf(newMember));
	}

}