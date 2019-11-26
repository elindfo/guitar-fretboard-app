package model.scale;

import model.Note;

public abstract class AbstractScale implements Scale{
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(getScaleName());
        sb.append("\n");
        sb.append("Key Signature: ");
        sb.append(getKey());
        sb.append("\n");
        sb.append("Scale notes: ");
        for(Note n : getNotes()){
            sb.append(n);
            sb.append(" ");
        }
        return sb.toString();
    }
}
