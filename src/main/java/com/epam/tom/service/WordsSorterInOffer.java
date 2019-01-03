package com.epam.tom.service;

import com.epam.tom.entity.NodeType;
import com.epam.tom.entity.TextComponent;
import com.epam.tom.entity.impl.TextPart;
import com.epam.tom.exception.NotSupportedSortException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class WordsSorterInOffer implements TOMSorter {

    @Override
    public void sort(TextPart component, Comparator<TextComponent> comparator) throws NotSupportedSortException {
        NodeType nodeType = component.getNodeType();
        int nestingLevel = nodeType.getLevelOfNesting();
        int nestingLevelOfOffer = NodeType.OFFER.getLevelOfNesting();
        if(nestingLevel > nestingLevelOfOffer){
            throw new NotSupportedSortException("Cannot sort words in TOM for root item: " + nodeType);
        }
        recursiveSort(component,comparator);

    }

    private void recursiveSort(TextPart component, Comparator<TextComponent> comparator){
        NodeType nodeType = component.getNodeType();
        int nestingLevel = nodeType.getLevelOfNesting();
        int nestingLevelOfOffer = NodeType.OFFER.getLevelOfNesting();

        if(nestingLevel < nestingLevelOfOffer){
            List<TextComponent> childComponents = component.getTextComponent();

            for (TextComponent part: childComponents ){
                recursiveSort((TextPart) part,comparator);
            }
        } else {


            wordsSort(component,comparator);
        }
    }

    private void wordsSort(TextPart component, Comparator<TextComponent> comparator){
        List<TextComponent> leafs = initListOfAllLeafItems(component);

        List<TextComponent> words = leafs.stream().
                filter((leaf)->leaf.getNodeType()==NodeType.WORD).
                sorted(comparator).
                collect(Collectors.toList());
        changeWordsInOffer(component,words);
    }


    private List<TextComponent> initListOfAllLeafItems(TextPart component){
        List<TextComponent> allLeafs = new ArrayList<>();
        for(TextComponent childNode: component.getTextComponent()){
            TextPart onePart = (TextPart)childNode;
            List<TextComponent> leafs = onePart.getTextComponent();
            allLeafs.addAll(leafs);
        }
        return allLeafs;
    }

    private void changeWordsInOffer(TextPart component,List<TextComponent> words) {
        List<TextComponent> lexemes = component.getTextComponent();
        for (int i = 0; i < lexemes.size(); ++i) {
            TextPart lexeme = (TextPart) lexemes.get(i);
            List<TextComponent> leafs = lexeme.getTextComponent();
            for (int j = 0; j < leafs.size(); ++j) {
                if (leafs.get(j).getNodeType() == NodeType.WORD) {
                    leafs.set(j, words.get(0));
                    words.remove(0);
                }
            }
        }
    }
}
