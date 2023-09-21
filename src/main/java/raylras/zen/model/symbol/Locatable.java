package raylras.zen.model.symbol;

import raylras.zen.util.Range;

import java.nio.file.Path;

public interface Locatable {

    Path getPath();

    String getUri();

    Range getRange();

    Range getSelectionRange();

}
