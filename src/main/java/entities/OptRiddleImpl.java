/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author sofieamalielandt
 */
@Entity
@Table(name = "OPTRIDDLE")
@NamedQuery(name = "OptRiddleImpl.deleteAllRows", query = "DELETE from OptRiddleImpl")
class OptRiddleImpl extends RiddleImpl implements Serializable {

    @Column(name = "options", updatable = false, nullable = false)
    protected List<String> options;

    public OptRiddleImpl() {
    }

    public OptRiddleImpl(String riddle, String answer, String hint, int riddleLevel, List<String> options) {
        this.uid = UUID.randomUUID();
        this.riddle = riddle;
        this.answer = answer;
        this.hint = hint;
        this.riddleLevel = riddleLevel;
        this.options = options;

    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }
    
    

}
