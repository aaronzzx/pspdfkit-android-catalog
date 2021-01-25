/*
 *   Copyright © 2020-2021 PSPDFKit GmbH. All rights reserved.
 *
 *   THIS SOURCE CODE AND ANY ACCOMPANYING DOCUMENTATION ARE PROTECTED BY INTERNATIONAL COPYRIGHT LAW
 *   AND MAY NOT BE RESOLD OR REDISTRIBUTED. USAGE IS BOUND TO THE PSPDFKIT LICENSE AGREEMENT.
 *   UNAUTHORIZED REPRODUCTION OR DISTRIBUTION IS SUBJECT TO CIVIL AND CRIMINAL PENALTIES.
 *   This notice may not be removed from this file.
 */

package com.pspdfkit.catalog.examples.java.activities;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;

import com.pspdfkit.annotations.Annotation;
import com.pspdfkit.annotations.AnnotationType;
import com.pspdfkit.catalog.R;
import com.pspdfkit.catalog.examples.java.AnnotationSelectionViewStylingExample;
import com.pspdfkit.configuration.theming.AnnotationSelectionViewThemeConfiguration;
import com.pspdfkit.ui.PdfActivity;
import com.pspdfkit.ui.special_mode.controller.AnnotationSelectionController;
import com.pspdfkit.ui.special_mode.manager.AnnotationManager;

/**
 * This activity shows how to customize the annotation selection view programmatically. Also, have a look at the {@link
 * AnnotationSelectionViewStylingExample} class.
 */
public class AnnotationSelectionViewStylingActivity extends PdfActivity implements AnnotationManager.OnAnnotationSelectedListener {
    private Drawable collaborate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        collaborate = AppCompatResources.getDrawable(this, R.drawable.ic_collaborate);
        requirePdfFragment().addOnAnnotationSelectedListener(this);
    }

    /**
     * Called immediately before annotation is going to be selected.
     *
     * @param controller        Selection controller that is performing the selection.
     * @param annotation        Annotation that is going to be selected.
     * @param annotationCreated {@code true} if the annotation is being created.
     * @return {@code true} when you want {@link AnnotationSelectionController} to proceed with the selection. Returning {@code false} will
     * prevent annotation from being selected.
     */
    @Override
    public boolean onPrepareAnnotationSelection(@NonNull AnnotationSelectionController controller,
                                                @NonNull Annotation annotation,
                                                boolean annotationCreated) {
        // Extract current annotation selection view theme configuration.
        AnnotationSelectionViewThemeConfiguration extractedCurrentConfiguration = controller.getAnnotationSelectionViewThemeConfiguration();
        // Build a new annotation selection view theme configuration using the current configuration as a base configuration.
        AnnotationSelectionViewThemeConfiguration.Builder annotationSelectionViewThemeConfiguration = new AnnotationSelectionViewThemeConfiguration.Builder(extractedCurrentConfiguration);
        // Customize the bottom right scale handle drawable depending on the annotation type.
        if (annotation.getType() == AnnotationType.STAMP) {
            annotationSelectionViewThemeConfiguration.setRotationHandleDrawable(null);
            annotationSelectionViewThemeConfiguration.setBottomRightScaleHandleDrawable(collaborate);
        }
        // Apply the new configuration to the annotation selection view.
        controller.setAnnotationSelectionViewThemeConfiguration(annotationSelectionViewThemeConfiguration.build());
        return true;
    }

    @Override
    public void onAnnotationSelected(@NonNull Annotation annotation, boolean annotationCreated) {
        // Not used.
    }
}
