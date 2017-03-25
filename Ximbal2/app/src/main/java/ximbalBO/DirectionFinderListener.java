package ximbalBO;

import java.util.List;

/**
 * Created by joseph on 04/03/2017.
 */

public interface DirectionFinderListener {
    void onDirectionFinderStart();
    void onDirectionFinderSuccess(List<Rutas> route);
}
