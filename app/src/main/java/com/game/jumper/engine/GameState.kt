package com.game.jumper.engine

// Feeling cute, might deprecate
enum class State {
    INITIALIZE,
    PLAYING,
    PAUSED,
    GAME_OVER,
    RESTART,
    QUIT
}

class GameState {
    private var gameState : State
    private var prevState : State
    private var nextState : State
    private var score : Int
    init {
        gameState = State.INITIALIZE
        prevState = gameState
        nextState = prevState
        score = 0
    }

    fun reset()
    {
        gameState = State.INITIALIZE
        prevState = gameState
        nextState = prevState
        score = 0
    }

    fun update()
    {
        while(gameState != State.QUIT)
        {
            if(gameState == State.RESTART)
            {
                gameState = prevState
                nextState = prevState
            }
            else {
                // Update state manager
                // Load scene
            }
            // Initialize scene
            while(gameState == nextState) // Game loop
            {
                // Input handle
                // Update scene
                // Draw scene
            }

            // Free scene
            if(nextState != State.RESTART) {
                // Unload scene
            }
            prevState = gameState
            gameState = nextState
        }
    }
}