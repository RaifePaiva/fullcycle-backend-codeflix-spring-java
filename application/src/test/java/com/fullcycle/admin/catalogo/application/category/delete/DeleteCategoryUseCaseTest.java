package com.fullcycle.admin.catalogo.application.category.delete;

import com.fullcycle.admin.catalogo.application.UseCaseTest;
import com.fullcycle.admin.catalogo.domain.category.Category;
import com.fullcycle.admin.catalogo.domain.category.CategoryGateway;
import com.fullcycle.admin.catalogo.domain.category.CategoryID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

class DeleteCategoryUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultDeleteCategoryUseCase useCase;

    @Mock
    private CategoryGateway categoryGateway;

    @Override
    protected List<Object> getMocks() {
        return List.of(categoryGateway);
    }

    @Test
    void givenAValidId_whenCallsDeleteCategory_shouldBeOK(){
        final var aCategory =  Category.newCategory("Filmes",
                "A categoria mais assistida", true);
        final var expectedId = aCategory.getId();

        Mockito.doNothing()
                .when(categoryGateway).deleteById(eq(expectedId));

        Assertions.assertDoesNotThrow(() -> useCase.execute(expectedId.getValue()));

        verify(categoryGateway, Mockito.times(1))
                .deleteById(eq(expectedId));

    }

    @Test
    void givenAInvalidId_whenCallsDeleteCategory_shouldBeOK(){
        final var expectedId = CategoryID.from("123");

        Mockito.doNothing()
                .when(categoryGateway).deleteById(eq(expectedId));

        Assertions.assertDoesNotThrow(() -> useCase.execute(expectedId.getValue()));

        verify(categoryGateway, Mockito.times(1))
                .deleteById(eq(expectedId));
    }

    @Test
    void givenAValidId_whenGatewayThrowsException_shouldReturnException(){
        final var aCategory =  Category.newCategory("Filmes",
                "A categoria mais assistida", true);
        final var expectedId = aCategory.getId();

        doThrow(new IllegalStateException("Gateway error"))
                .when(categoryGateway).deleteById(eq(expectedId));

        assertThrows(IllegalStateException.class, () -> useCase.execute(expectedId.getValue()));

        verify(categoryGateway, Mockito.times(1))
                .deleteById(eq(expectedId));
    }



}
