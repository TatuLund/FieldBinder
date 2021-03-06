package org.vaadin.fieldbinder;

import java.util.EventObject;

import com.vaadin.data.Binder;
import com.vaadin.data.StatusChangeEvent;
import com.vaadin.data.StatusChangeListener;
import com.vaadin.data.ValueProvider;
import com.vaadin.data.Binder.Binding;
import com.vaadin.data.Binder.BindingBuilder;
import com.vaadin.server.Setter;

/**
 * Binder status change event.
 * <p>
 * The {@link FieldBinder} status is changed whenever any of the following happens:
 * <ul>
 * <li>if any of its bound fields or selects have been changed 
 * <li>FieldBindingBuilder#bind(Object) is called
 * <li>FieldBinder#validate() or FieldBinding#validate() is called
 * </ul>
 *
 * @see StatusChangeListener#statusChange(StatusChangeEvent)
 * @see Binder#addStatusChangeListener(StatusChangeListener)
 *
 */
public class FieldBinderStatusChangeEvent extends EventObject {

    private final boolean hasValidationErrors;

    /**
     * Create a new status change event for given {@code binder}, storing
     * information of whether the change that triggered this event caused
     * validation errors.
     *
     * @param binder
     *            the event source binder
     * @param hasValidationErrors
     *            the validation status associated with this event
     */
    public FieldBinderStatusChangeEvent(FieldBinder binder, boolean hasValidationErrors) {
        super(binder);
        this.hasValidationErrors = hasValidationErrors;
    }

    /**
     * Gets the associated validation status.
     *
     * @return {@code true} if the change that triggered this event caused
     *         validation errors, {@code false} otherwise
     */
    public boolean hasValidationErrors() {
        return hasValidationErrors;
    }

    @Override
    public FieldBinder getSource() {
        return (FieldBinder) super.getSource();
    }

    /**
     * Gets the binder.
     *
     * @return the binder
     */
    public FieldBinder getBinder() {
        return getSource();
    }

}
