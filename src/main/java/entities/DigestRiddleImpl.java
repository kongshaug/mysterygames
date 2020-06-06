/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.interfaces.DigestRiddle;
import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import groovy.util.Eval;
import javax.persistence.Column;
import javax.ws.rs.WebApplicationException;
import org.codehaus.groovy.control.CompilationFailedException;

/**
 *
 * @author benja
 */
@Entity
@Table(name = "DIGESTRIDDLE")
@NamedQuery(name = "DigestRiddleImpl.deleteAllRows", query = "DELETE from DigestRiddleImpl")
class DigestRiddleImpl extends RiddleImpl implements Serializable, DigestRiddle {

    @Column(name = "funct", updatable = false, nullable = false)
    protected String funct;

    DigestRiddleImpl() {
    }

    DigestRiddleImpl(String riddle, String answer, String hint, int riddleLevel, String funct) {
        this.uid = UUID.randomUUID();
        this.riddle = riddle;
        this.answer = answer;
        this.hint = hint;
        this.riddleLevel = riddleLevel;
        this.funct = funct;
       
    }

    public String getFun() {
        return funct;
    }

    public void setFun(String funct) {
        this.funct = funct;
    }

    @Override
    public String digest(String input) {
        try {
        int i = Integer.parseInt(input);
        String output = Eval.x(i, this.funct).toString();
        return output; 
        } catch (NumberFormatException | CompilationFailedException e) {
            throw new WebApplicationException("Input must be a number", 400);
        }
    }
    @Override
    public boolean validate(String answer) {

        return answer.toLowerCase().replaceAll(" ","").equals(this.answer.toLowerCase().replaceAll(" ","")) || answer.toLowerCase().replaceAll(" ","").equals(this.funct.toLowerCase().replaceAll(" ",""));
    }
    
    @Override
    public RiddleDTO toDTO() {
        return new DigestRiddleDTO(this);
    }

}
