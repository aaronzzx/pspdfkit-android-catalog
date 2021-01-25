/*
 *   Copyright © 2020-2021 PSPDFKit GmbH. All rights reserved.
 *
 *   THIS SOURCE CODE AND ANY ACCOMPANYING DOCUMENTATION ARE PROTECTED BY INTERNATIONAL COPYRIGHT LAW
 *   AND MAY NOT BE RESOLD OR REDISTRIBUTED. USAGE IS BOUND TO THE PSPDFKIT LICENSE AGREEMENT.
 *   UNAUTHORIZED REPRODUCTION OR DISTRIBUTION IS SUBJECT TO CIVIL AND CRIMINAL PENALTIES.
 *   This notice may not be removed from this file.
 */

package com.pspdfkit.catalog.examples.java;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.annotation.NonNull;

import com.pspdfkit.catalog.PSPDFExample;
import com.pspdfkit.catalog.R;

import com.pspdfkit.catalog.examples.java.activities.AnnotationSelectionViewStylingActivity;
import com.pspdfkit.catalog.tasks.ExtractAssetTask;
import com.pspdfkit.configuration.activity.PdfActivityConfiguration;
import com.pspdfkit.ui.PdfActivityIntentBuilder;

/**
 * This example shows how to customize the annotation selection view.
 */
public class AnnotationSelectionViewStylingExample extends PSPDFExample {

    public AnnotationSelectionViewStylingExample(@NonNull final Context context) {
        super(context.getString(R.string.annotationSelectionViewStylingExampleTitle),
            context.getString(R.string.annotationSelectionViewStylingExampleDescription));
    }

    @Override
    public void launchExample(@NonNull final Context context,
                              @NonNull final PdfActivityConfiguration.Builder configuration) {
        // Set the new theme that overrides annotation selection color,
        // scale handle drawables and background drawable.
        configuration.theme(R.style.AnnotationSelectionExample_Theme);

        // Extract the example document from the app's assets.
        ExtractAssetTask.extract("Annotation-Selection.pdf", title, context, documentFile -> {
            final Intent intent = PdfActivityIntentBuilder.fromUri(context, Uri.fromFile(documentFile))
                .configuration(configuration.build())
                .activityClass(AnnotationSelectionViewStylingActivity.class)
                .build();

            context.startActivity(intent);
        });
    }
}
