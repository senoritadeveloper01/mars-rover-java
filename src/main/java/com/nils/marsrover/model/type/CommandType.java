package com.nils.marsrover.model.type;

import java.util.Arrays;
import java.util.Optional;

/**
 * Enum class for direction commands (Left, Right, Move)
 * @author  Nil Seri
 */
public enum CommandType {

    LEFT('L'),
    RIGHT('R'),
    MOVE('M');

    private final Character key;

    CommandType(Character key) {
        this.key = key;
    }

    public static CommandType getCommandByKey(Character key){
        Optional<CommandType> typeOptional = Arrays.stream(CommandType.values()).filter(x -> x.key.equals(key)).findFirst();
        return typeOptional.isPresent() ? typeOptional.get() : null;
    }

}
