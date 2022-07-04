package hr.tvz.android.linxproject.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import hr.tvz.android.linxproject.R

class LinXWidget: AppWidgetProvider() {
    fun updateAppWidget(
        context: Context, appWidgetManager: AppWidgetManager,
        appWidgetId: Int
    ) {
        val widgetText: CharSequence = context.getString(R.string.widget_text)
        val views = RemoteViews(context.packageName, R.layout.linx_widget)
        views.setTextViewText(R.id.widgetTextView, widgetText)
        appWidgetManager.updateAppWidget(appWidgetId, views)
    }

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }
}