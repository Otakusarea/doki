import android.content.ClipData
import android.view.DragEvent
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

class DragDropListener : View.OnTouchListener, View.OnDragListener {
    private var initialX = 0f
    private var initialY = 0f

    override fun onTouch(view: View, event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                val data = ClipData.newPlainText("", "")
                val shadowBuilder = View.DragShadowBuilder(view)
                view.startDrag(data, shadowBuilder, view, 0)
                view.visibility = View.INVISIBLE
            }
            else -> return false
        }
        return true
    }

    override fun onDrag(view: View, event: DragEvent): Boolean {
        val draggedView = event.localState as View
        val targetView = view as LinearLayout
        val parentView = targetView.parent as LinearLayout

        when (event.action) {
            DragEvent.ACTION_DRAG_STARTED -> {}
            DragEvent.ACTION_DRAG_ENTERED -> {}
            DragEvent.ACTION_DRAG_EXITED -> {}
            DragEvent.ACTION_DROP -> {
                val draggedParentView = draggedView.parent as LinearLayout
                draggedParentView.removeView(draggedView)
                parentView.addView(draggedView)
                draggedView.visibility = View.VISIBLE

                // Aktualisiere das übergeordnete Steuerelement des gezogenen Elements
                //draggedParentView.invalidate()
            }
            DragEvent.ACTION_DRAG_ENDED -> {
                draggedView.visibility = View.VISIBLE

                // Aktualisiere das übergeordnete Steuerelement des Zielbereichs
                //parentView.invalidate()
            }
        }
        return true
    }
}
