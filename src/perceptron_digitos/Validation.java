package perceptron_digitos;

public class Validation {
    
    public Validation(){
        
    }
    
    public double summation(int mat[][], double weights[][], int neuron) {
        double yInput = 0;
        double input[] = new double[16];
        int l = 1;
        input[0] = 1;
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 3; j++) {
                input[l] = mat[i][j];
                l++;
            }
        for (int j = 0; j < 16; j++)
            yInput = yInput + input[j] * weights[j][neuron];
        return yInput;
    }

    public double output(double yInput, double threshold) {
        double f;
        if (yInput > threshold)
            f = 1;
        else if (yInput < -threshold)
            f = -1;
        else
            f = 0;
        return f;
    }

    public String test(int mat[][], double weights[][], double targets[][], double threshold) {
        
        double[] yInput = new double[4];
        double[] f = new double[4];
        
        for (int x = 0; x < 4; x++) {
             yInput[x] = summation(mat, weights, x); 
             f[x] = output(yInput[x], threshold);    
        }
        
        
        for (int y = 0; y < targets.length; y++) {
            if (f[0] == targets[y][0] && f[1] == targets[y][1] && f[2] == targets[y][2] && f[3] == targets[y][3]) {
                System.out.print("f[0]=" + f[0] + ", f[1]=" + f[1]+ ", f[2]=" + f[2]+ ", f[3]=" + f[3] );
                System.out.print(" | t["+y+"]=" + targets[y][0] + ", t["+y+"]=" + targets[y][1]+ ", t["+y+"]=" + targets[y][2]+ ", t["+y+"]=" + targets[y][3] + "\n");

                return String.valueOf(y);
            }
        }
        return "?"; 
    }     
}