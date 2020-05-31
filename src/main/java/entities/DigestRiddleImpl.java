/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.UUID;
import java.util.function.Function;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author benja
 */
@Entity
@Table(name = "DIGESTRIDDLE")
@NamedQuery(name = "DigestRiddleImpl.deleteAllRows", query = "DELETE from DigestRiddleImpl")
class DigestRiddleImpl extends RiddleImpl implements Serializable {
    
    @Column(name = "funct", updatable = false, nullable = false)
    protected Function<String, String> fun;
  
    DigestRiddleImpl() {
    }
                
    DigestRiddleImpl(String riddle, String answer, String hint, int riddleLevel,Function<String,String> fun ) {
        this.uid = UUID.randomUUID();
        this.riddle = riddle;
        this.answer = answer;
        this.hint = hint;
        this.riddleLevel = riddleLevel;
        this.fun = fun;
    }
    
    
}
