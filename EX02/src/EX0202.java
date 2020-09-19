// Stephen Haugland
// 9/18/2020
// Software QA - Pete Tucker
// Whitworth University
// EX02 problem 2

import java.util.*;





public class EX0202{

    // declare enumerated types of triangles
    public enum Triangle { NotATriangle, Scalene, Right, Isosceles, Equilateral}

    public static Triangle isTriangle(int a, int b, int c){
        // ensure sides have a valid length (non-negative lengths)
        if (a > 0 && b > 0 && c > 0){
            // triangle inequality theorem
            if((a + b > c) && (b + c > a) && (a + c > b)){
                // equilateral triangle check
                if (a == b && a == c){
                    return Triangle.Equilateral;
                }
                // isosceles triangle check
                else if (a == b || a == c || b == c){
                    return Triangle.Isosceles;
                }
                // right triangle check (all variations of A^2 + B^2 = C^2)
                else if (((a*a)+(b*b) == (c*c)) || ((a*a) + (c*c) == (b*b)) || ((b*b) + (c*c) == (a*a))){
                    return Triangle.Right;
                }
                // must be scalene triangle if valid triangle and is no other types
                else {
                    return Triangle.Scalene;
                }
            }
        }
        // if sides are invalid lengths or cannot make a triangle, this is not a triangle
        return Triangle.NotATriangle;
    }
    
    // Testing structure sourced from Pete Tucker
    // For structure testing approach
    public class triangleSides {
        int a,b,c;
        triangleSides(int _a, int _b, int _c) {
            a = _a;
            b = _b;
            c = _c;
        } 
    };


    // Testing structure sourced from Pete Tucker
    // For structure testing approach
    public class triangleSideTest {
        triangleSides sides;
        Triangle expectedResult;
    
        triangleSideTest(triangleSides _sides, Triangle _exp){
            sides = _sides;
            expectedResult = _exp;
        } 
    };

    static void runTest(triangleSideTest test) {
        Triangle act = isTriangle(test.sides.a, test.sides.b, test.sides.c);
        if (act != test.expectedResult) {
            System.out.println("Test FAILED");
            System.out.println("\t<" + test.sides.a + ", " + test.sides.b + ", " + test.sides.c + ">");
            System.out.println("\texp: " + test.expectedResult);
            System.out.println("\tact: " + act);
        }
    }

    public static void main(String []args){
        // Simple tests
        if (isTriangle(1, 2, 3) != Triangle.NotATriangle){
            System.out.println("Failed for <1,2,3>");
        }
    
        if (isTriangle(3, 3, 3) != Triangle.Equilateral){
            System.out.println("Failed for <3,3,3>");
        }

        if (isTriangle(3, 4, 5) != Triangle.Right){
            System.out.println("Failed for <3,4,5>");
        }

        if (isTriangle(5, 6, 7) != Triangle.Scalene){
            System.out.println("Failed for <5,6,7>");
        }

        // Structure testing approach
        Vector<triangleSideTest> tests = new Vector<triangleSideTest>();

        // this smells fishy, a little rusty in Java
        EX0202 test = new EX0202();
        // load test cases with expected values into vector
        tests.addElement(test.new triangleSideTest(test.new triangleSides(1,1,1), Triangle.Equilateral));
        tests.addElement(test.new triangleSideTest(test.new triangleSides(-1,1,1), Triangle.NotATriangle));
        tests.addElement(test.new triangleSideTest(test.new triangleSides(1,-1,1), Triangle.NotATriangle));
        tests.addElement(test.new triangleSideTest(test.new triangleSides(1,1,-1), Triangle.NotATriangle));
        tests.addElement(test.new triangleSideTest(test.new triangleSides(3,4,5), Triangle.Right));
        tests.addElement(test.new triangleSideTest(test.new triangleSides(5,5,5), Triangle.Equilateral));
        tests.addElement(test.new triangleSideTest(test.new triangleSides(0,1,1), Triangle.NotATriangle));
        tests.addElement(test.new triangleSideTest(test.new triangleSides(-1,-1,-1), Triangle.NotATriangle));
        tests.addElement(test.new triangleSideTest(test.new triangleSides(10,10,5), Triangle.Isosceles));
        tests.addElement(test.new triangleSideTest(test.new triangleSides(7,10,5), Triangle.Scalene));
        tests.addElement(test.new triangleSideTest(test.new triangleSides(4,6,7), Triangle.Scalene));
        tests.addElement(test.new triangleSideTest(test.new triangleSides(Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE), Triangle.NotATriangle));

        

        // run through the vector of test cases and compare expected result to actual result
        for (int i = 0; i < tests.size(); i++){
            runTest(tests.elementAt(i));
        }
    }
}