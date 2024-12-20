/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prototype;
import model.Task;
/**
 *
 * @author ascom
 */
public class PrototypeManager {
    private Task prototypeTask;

    public PrototypeManager(Task prototypeTask) {
        this.prototypeTask = prototypeTask;
    }

    public Task cloneTask() throws CloneNotSupportedException {
        return prototypeTask.clone();
    }

    public void setPrototypeTask(Task prototypeTask) {
        this.prototypeTask = prototypeTask;
    }
}
