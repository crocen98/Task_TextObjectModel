package com.epam.tom.service;

import com.epam.tom.entity.NodeType;
import com.epam.tom.entity.TextComponent;
import com.epam.tom.entity.impl.TextPart;
import com.epam.tom.exception.IncorrectNestingException;

import java.util.List;

public class TOMBuilder {
    private TextPart root;
    private NodeType type;

    public TOMBuilder(TextPart root){
        this.root = root;
        this.type = root.getNodeType();
    }
    public TOMBuilder(){}
    public TextPart getComponent(){
        return this.root;
    }

    public void append(TextComponent component) throws IncorrectNestingException {
        if (root == null){
           root = (TextPart) component;
           type = root.getNodeType();
        } else {
            recursiveAppend(component, root);
        }
    }

    private void recursiveAppend(TextComponent component, TextPart root) throws IncorrectNestingException {
        int componentLevel = component.getNodeType().getLevelOfNesting();
        int rootLevel = root.getNodeType().getLevelOfNesting();
        if(componentLevel <= rootLevel){
            throw new IncorrectNestingException("Is impossible to add " + component + " to " + root);
        } else if (componentLevel == rootLevel + 1){
            root.append(component);
        } else {
            List<TextComponent> children = root.getTextComponent();
            if(children.size() == 0 && componentLevel - rootLevel>1){
                throw new IncorrectNestingException("Is impossible to add " + component + " to " + root);
            }
            TextPart lastChild = (TextPart)children.get(children.size()-1);
            recursiveAppend(component,lastChild);
        }
    }

    public NodeType getType() {
        return type;
    }
}
