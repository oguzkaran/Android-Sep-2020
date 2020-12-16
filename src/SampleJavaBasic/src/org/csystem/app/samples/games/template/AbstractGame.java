package org.csystem.app.samples.games.template;

public abstract class AbstractGame implements IGame {
    private String m_name;
    //...
    public AbstractGame(String name)
    {
        m_name = name;
    }

    public String getName()
    {
        return m_name;
    }

    public void setName(String name)
    {
        m_name = name;
    }

    //...
    public final void run()
    {
        this.start();
        this.play();
        //...
        this.pause();
        //...
        this.end();
    }
}
