import java.util.*;
import javax.swing.*;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.io.*;

public class Common{
	public int windowWidth;
	public int windowHeight;
	public UniversityMap map;
	public List<Academician> academicians = new ArrayList<Academician>();
	public List<Speaker> speakers = new ArrayList<Speaker>();
	public List<Student> students = new ArrayList<Student>();
	public List<Assessment> assessments = new ArrayList<Assessment>();
	public Vector2D position;
	public State state;
	public Random random = new Random();
	public int finalpositionxStudent;
	public int finalpositionyStudent;
	public boolean isGraduation = false;
	public int studentNumber = 5;

	public Common(int numberOfStudents, int newwindowWidth, int newwindowHeight){

		String[] names = new String[] {"Saadat", "Zhadyra", "Daneker", "Aiya", "Nuray", "Lyailya", 
									   "Izat", "Saddam", "Ayan", "Daulet", "Nurtas", "Sherkhan"};
		
		this.studentNumber = numberOfStudents;

		this.windowWidth = newwindowWidth;
		this.windowHeight = newwindowHeight;

		try{
			map = new UniversityMap("NUMap-Faded.jpg", this.windowWidth, this.windowHeight);
		}
		catch(IOException e){}
		// int windowWidth = map.backgroundImage.getWidth(null);
		// int windowHeight = map.backgroundImage.getHeight(null);
		System.out.println(windowWidth);
		System.out.println(windowHeight);

		finalpositionxStudent = this.windowWidth - this.windowWidth/3;
		finalpositionyStudent = this.windowHeight - this.windowHeight/5;

		this.speakers.add(new Speaker("Nazarbayev", new Vector2D(finalpositionxStudent - this.windowWidth / 9, finalpositionyStudent), new Stationary(), this, "NursultanNazarbayev.gif"));
		this.speakers.add(new Speaker("Tokayev", new Vector2D(finalpositionxStudent + this.windowWidth / 9, finalpositionyStudent), new Stationary(), this, "KassymJomartTokayev.gif"));

		int aposx = getranposition(this.windowWidth);
		int aposy = getranposition(this.windowHeight);
		this.academicians.add(new Academician("Temizer", new Vector2D(aposx, aposy), new Rest(2), this, "SelimTemizer.gif"));
		
		aposx = getranposition(windowWidth);
		aposy = getranposition(windowHeight);
		this.academicians.add(new Academician("Nivelle", new Vector2D(aposx, aposy), new Rest(2), this, "HansDeNivelle.gif"));

		aposx = getranposition(windowWidth);
		aposy = getranposition(windowHeight);
		this.academicians.add(new Academician("Katsu", new Vector2D(aposx, aposy), new Rest(2), this, "ShigeoKatsu.gif"));

		aposx = getranposition(windowWidth);
		aposy = getranposition(windowHeight);
		this.academicians.add(new Academician("Tourassis", new Vector2D(aposx, aposy), new Rest(2), this, "VassiliosTourassis.gif"));

		for(int i = 0; i < this.studentNumber; i++){
			int posx = getranposition(this.windowWidth);
			int posy = getranposition(this.windowHeight);
			this.students.add(new Student(names[random.nextInt(names.length)], new Vector2D(posx, posy), new Rest(4), this));
		}
	}

	public int getranposition(int max){
		int ran = random.nextInt(max);
		if (ran > max - max/10) ran -= max/10;
		if (ran < max/10) ran+=max/10;
		return ran;
	}

	public State newstudentstate(Student student){
		int ran = random.nextInt(4);
		if (ran == 1){
			int newposx = getranposition(this.windowWidth), newposy = getranposition(this.windowHeight);
			return new GotoXY(new Vector2D( newposx, newposy));
		}
		else if (ran == 2){
			Vector2D newposition = new Vector2D(0, 0);
			double distance = Double.POSITIVE_INFINITY;
			for(Assessment assessment : assessments){
				if (student.position.distanceTo(assessment.position) < distance){
					distance = student.position.distanceTo(assessment.position);
					newposition = assessment.position;
				}
			}
			if (newposition.x != 0 && newposition.y != 0) return new Closest(newposition, random.nextInt(3)+1);
			return new Rest(2);
		}
		else if (ran == 0){
			int newposx = random.nextInt(1000) - random.nextInt(1000), newposy = random.nextInt(1000) - random.nextInt(1000);
			return new ZigZag(new Vector2D( newposx, newposy), random.nextInt(300));
		}
		else return new Rest(random.nextInt(300));
	}

	public State newAcademicState(){
		Random random = new Random();
		int ran = random.nextInt(3);
		if (ran == 1){
			int newposx = getranposition(this.windowWidth), newposy = getranposition(this.windowHeight);
			return new GotoXY(new Vector2D( newposx, newposy));
		}
		else if (ran == 2){
			int newposx = random.nextInt(1000) - random.nextInt(1000), newposy = random.nextInt(1000) - random.nextInt(1000);
			return new ZigZag(new Vector2D( newposx, newposy), random.nextInt(300));
		}
		else return new Rest(random.nextInt(300));
	}

	public void stepAllEntities(){

		for (Academician academician : academicians){
			academician.step();
			
			if (academician.state.isOver){
				academician.state = newAcademicState();
			}
			int num = getranposition(1000);
			if (academician.state.toString().equals("GotoXY") || academician.state.toString().equals("ZigZag")){
				if (num % 50 == 0) assessments.add(academician.createAssessment());
			}
		}

		int numberStudentsFinished = 0;
		for (Student student : students){
			if (student.grade >= 100 && student.state.toString().equals("Stationary")){
				numberStudentsFinished++;
				continue;
			}
			student.step();
			if (student.state.isOver){
				if (student.grade >= 100 && student.position.x == finalpositionxStudent && student.position.y == finalpositionyStudent){
					student.state = new Stationary();
				}
				else if (student.grade >= 100){
					student.state = new GotoXY(new Vector2D( finalpositionxStudent, finalpositionyStudent));
				}
				else student.state = newstudentstate(student);
			}
			List<Assessment> delete = new ArrayList<Assessment>();
			if (student.grade>=100) continue;

			for (Assessment assessment : assessments){
				// System.out.println(student.position.distanceTo(assessment.position));
				if (student.position.distanceTo(assessment.position) < 12){
					student.grade += assessment.points;
					delete.add(assessment);
				}
			}

			for (Assessment d : delete){
				assessments.remove(d);
			}

			if (student.state.toString().equals("Closest") && student.state.requirenew == true) {
				Vector2D newposition = new Vector2D(0, 0);
				double distance = Double.POSITIVE_INFINITY;
				for(Assessment assessment : assessments){
					if (student.position.distanceTo(assessment.position) < distance){
						distance = student.position.distanceTo(assessment.position);
						newposition = assessment.position;
					}
				}
				if (newposition.x != 0 && newposition.y != 0) student.state.set(newposition);
				else student.state = new Rest(3);
			}
		}
		if (numberStudentsFinished == this.studentNumber){
			this.isGraduation = true;
		}
	}

	public void drawAllEntities( Graphics2D g2d){
		map.draw(g2d);
		for(Student student : students){
			student.draw(g2d);
		}
		if (isGraduation){
			int academicianPosY = this.windowHeight - this.windowHeight / 3 - this.windowHeight / 10;
			int academicianPosX = finalpositionxStudent - this.windowWidth / 9;
			for(Academician academician : academicians){
				academician.position.y = academicianPosY;
				academician.position.x = academicianPosX;
				academician.state = new Stationary();
				academician.draw(g2d);
				academicianPosX += academician.imgwidth * 1.5;
			}
			for(Speaker speaker : speakers){
				speaker.draw(g2d);
			}

			return;
		}
		for(Academician academician : academicians){
			academician.draw(g2d);
		}
		int i = 0;
		for(Assessment assessment : assessments){
			assessment.draw(g2d);
		}
	}
	
}
