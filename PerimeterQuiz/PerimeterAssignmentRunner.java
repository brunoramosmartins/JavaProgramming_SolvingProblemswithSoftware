import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int count = 0;
        for (Point currPt : s.getPoints()) {
            count++;
        }
        return count;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        int sides = getNumPoints(s);
        double perim = getPerimeter(s);
        double average = perim / sides;
        return average;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        // Start with totalPerim = 0
        double largeSide = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update largeSide by currDist
            if (currDist > largeSide) largeSide = currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        return largeSide;
    }

    public double getLargestX(Shape s) {
        // Put code here
        Point prevPt = s.getLastPoint();        
        double largeX = prevPt.getX();
        
        for (Point currPt : s.getPoints()) {
            double tempX = currPt.getX();
            if (tempX > largeX) largeX = tempX;
        }
        return largeX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        double BigP = 0.0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            if( getPerimeter(s) > BigP) {
                BigP = getPerimeter(s);
            }
        }
        return BigP;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File temp = null;    // replace this code
        DirectoryResource dr = new DirectoryResource();
        double BigP = 0.0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            if( getPerimeter(s) > BigP) {
                BigP = getPerimeter(s);
                temp = f;
            }
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int sides = getNumPoints(s);
        double average = getAverageLength(s);
        double largeSide = getLargestSide(s);
        double largeX = getLargestX(s);
        System.out.println("perimeter = " + length);
        System.out.println("sides = " + sides);
        System.out.println("average = " + average);
        System.out.println("large side = " + largeSide);
        System.out.println("large X = " + largeX);
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        double p = getLargestPerimeterMultipleFiles();
        System.out.println();
        System.out.println("large Perimeter the multiple files is " + p);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        String f = getFileWithLargestPerimeter();
        System.out.println();
        System.out.println("file with large perimeter is " + f);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
        pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
        
    }
}
