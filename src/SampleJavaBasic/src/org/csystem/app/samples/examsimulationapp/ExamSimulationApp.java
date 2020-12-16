package org.csystem.app.samples.examsimulationapp;

public final class ExamSimulationApp {
    private ExamSimulationApp()
    {
    }

    public static void run()
    {
        ExamSimulation examSimulation = new ExamSimulation("Matematik");

        examSimulation.run();
        examSimulation.displayGrades();
        examSimulation.displayStatus();
    }
}
