package perceptron_digitos;

public class Learning {
    
    private double inputs[][]= {
        {1,1,1,1,1,0,1,1,0,1,1,0,1,1,1,1},
        {1,0,1,0,1,1,0,0,1,0,0,1,0,1,1,1},
        {1,1,1,1,0,0,1,1,1,1,1,0,0,1,1,1},
        {1,1,1,1,0,0,1,1,1,1,0,0,1,1,1,1},
        {1,1,0,1,1,0,1,1,1,1,0,0,1,0,0,1},
        {1,1,1,1,1,0,0,1,1,1,0,0,1,1,1,1},
        {1,1,1,1,1,0,0,1,1,1,1,0,1,1,1,1},
        {1,1,1,1,0,0,1,0,0,1,0,0,1,0,0,1},
        {1,1,1,1,1,0,1,1,1,1,1,0,1,1,1,1},
        {1,1,1,1,1,0,1,1,1,1,0,0,1,1,1,1}
        
    };
    
    private double weights[][] = new double[16][4];
    
    private double targets[][] = {
        {1,1,1,1},
        {1,1,1,-1}, 
        {1,1,-1,1},
        {1,1,-1,-1},
        {1,-1,1,1},
        {1,-1,1,-1},
        {1,-1,-1,1},
        {1,-1,-1,-1},
        {-1,1,1,1},
        {-1,1,1,-1}
    };
    
    private int epochs;
        
    public Learning(){  
        epochs = 0;
    }
    
    public double[][] getWeights(){
        return weights;
    }
    
    public double[][] getTargets(){
        return targets;
    }    
    
    public int getEpochs(){
        return epochs;
    }
    
   public double summation(int i, int k){
        double yInput = 0;       
        for(int j = 0; j < 16; j++)
            yInput = yInput + inputs[i][j] * weights[j][k];
        return yInput;
    }

    public double output(double yInput, double threshold){
        double f;
        if (yInput > threshold)
            f = 1;
        else if (yInput < -threshold)
            f = -1;          
        else
            f = 0;
        return f;
    }
    
    public void update(double alpha, double f[], int i){
        for(int k = 0; k < 4; k++)
            for(int j = 0; j < 16; j++)
                weights[j][k] = weights[j][k] + alpha * (targets[i][k] - f[k]) * inputs[i][j];
    }
    
    public void algorithm(double alpha, double threshold){
        double yInput;
        double f[] = new double[4];
        boolean changed;       
       
        for(int j = 0; j < 16; j++)  // zero the weights
            for(int k = 0; k < 4; k++)
                weights[j][k] = 0;       
        do {
            changed = false;
            for(int i = 0; i < 10; i++) {   // iterate over all input patterns
                for(int k = 0; k < 4; k++) {  // iterate over the outputs
                    yInput = summation(i, k);
                    f[k] = output(yInput, threshold);
                }
                for(int k = 0; k < 4; k++) {  
                    if(f[k] != targets[i][k]) 
                      changed = true;
                    
                    if (changed) {
                        update(alpha, f, i); 
                    }
                }
            }
            
            epochs++;
        } while (changed);      
    }  
}