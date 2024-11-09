package Demofiles;

import java.util.Random;

public class RandomEmail {

	public static void main(String[] args) {
		
		String[] validEmail= {"amotooricap1@gmail.com","amotooricap2@gmail.com","amotooricap3@gmail.com",
				"amotooricap4@gmail.com","amotooricap5@gmail.com","amotooricap6@gmail.com",
				         "amotooricap7@gmail.com","amotooricap8@gmail.com"};
		
		Random r=new Random();
		
		int randomIndex=r.nextInt(8);
		System.out.println(randomIndex);
		
		//return validEmail[randomIndex];
		
		// return validEmail[new Random().nextInt(8)]; single line code
		
		
		

	}

}
