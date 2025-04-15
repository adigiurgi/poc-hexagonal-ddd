package io.undefvar.demohexagonalarchitecture.application.domain.service;

import io.undefvar.demohexagonalarchitecture.application.domain.models.Movie;

public class OtherBussinesLogicUsefullAcrossUseCases {
    public static void someOtherBussinesLogic(Movie movie) {
        // Some business logic that is useful across use cases
    }

    public static boolean someOtherBussinesLogicChecks(Movie movie) {
        // Some other business logic that is useful across use cases
        return true;
    }
}
