import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class GameNim extends Game {
  int WinningScore = 10;
  int LosingScore = -10;

  public GameNim() {
    currentState = new StateNim();
  }

  public boolean isWinState(State state) {
    //we just check if the pile is empty
    if (state.pile == 0){
      return true;
    }
    else {
      return false;
    }
  }

  public boolean isStuckState(State state) {
    //it is impossible to be stuck as we can always remove a coin
    return false;
  }

  public Set<State> getSuccessors(State state) {
    //TODO
  }

  public double eval(State state) {
    
  }
  
}
