import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class GameNim extends Game {
  int WinningScore = 10;
  int LosingScore = -10;
  int midGame = 0;

  public GameNim() {
    currentState = new StateNim();
  }

  public boolean isWinState(State state) {
    StateNim currentState = (StateNim) state;
    //we just check if the pile is empty
    if (currentState.getPileSize() == 0) {
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
    if (isWinState(state) || isStuckState(state)) {
      return null;
    }

    Set<State> successors = new HashSet<State>();
    StateNim oldState = (StateNim) state;

    //so, we want to remove 1, 2 and 3 coins as seperate states, provided the pile is large enough
    for (int i = 1; i<4; i++) {
      if (oldState.pile >= i) {
        StateNim successor_state = new StateNim(oldState);
        successor_state.changeState(oldState, i);
        successor_state.player = (state.player==0 ? 1 : 0);
        successors.add(successor_state);
      }
    }
    return successors;
  }

  public double eval(State state) {
    if (isWinState(state)) {
      int previous_player = (state.player==0 ? 1 : 0);
      if (previous_player == 1) {
        return WinningScore;
      }
      else {
        return LosingScore;
      }
    }
    return midGame;
  }

  public static void main(String[] args) throws Exception {
    Game game = new GameNim();
    Search search = new Search(game);
    int depth = 8; //subject to change

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    while(true) {
      StateNim nextState = null;

      switch(game.currentState.player) {
        //human
        case 1:
          System.out.print("Enter the number of *valid* stones you would like to remove: ");
          int numStones = Integer.parseInt( in.readLine() );
          nextState = new StateNim((StateNim)game.currentState);
          nextState.player = 1;
          nextState.changeState(nextState, numStones);
          System.out.println("Human: \n" + nextState);
          break;
        
        //computer
        case 0:
          nextState = (StateNim)search.bestSuccessorState(depth);
          nextState.player = 0;
          System.out.println("Computer: \n" + nextState);
          break;
      }

      game.currentState = nextState;
      game.currentState.player = (game.currentState.player==0 ? 1 : 0);

      //check who wins
      if ( game.isWinState(game.currentState) ) {
        if (game.currentState.player == 0) //i.e. last move was by the computer
          System.out.println("Computer wins!");
        else
          System.out.println("You win!");
        break;
      }
      
    }
    
  }
  
}
