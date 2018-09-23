package org.vaadin.fieldbinder.demo;

import org.vaadin.fieldbinder.FieldBinder;
import org.vaadin.fieldbinder.FieldBinder.FieldBinding;

import java.time.LocalDate;
import java.util.Date;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.converter.LocalDateToDateConverter;
import com.vaadin.data.converter.StringToDoubleConverter;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.data.validator.DateRangeValidator;
import com.vaadin.data.validator.DoubleRangeValidator;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("demo")
@Title("FieldBinder Add-on Demo")
@SuppressWarnings("serial")
public class DemoUI extends UI
{

	String textValue = ""; 
	Integer integerValue = 0;
	Double doubleValue = 0d;
	Date dateValue = new Date();
	
    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class)
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {

        // Binder with text
        FieldBinder<String> textFieldBinder = new FieldBinder<>();
        TextField textField = new TextField("Input text");

        // Text field with String value and validator
        // Demoing how to detect if value is valid and how to get it from FieldBinder
        textFieldBinder.forField(textField)
        		.asRequired()
        		.withValidator(new StringLengthValidator("Input needs to be between 5 and 10 characters",5,10))
        		.bind(textValue);
        textFieldBinder.addValueChangeListener(event ->  {
        	System.out.println("String: "+textValue);
        	System.out.println("String from event: "+event.getValue());
        	System.out.println("Valid Integer from binder: "+textFieldBinder.getValue());
        	System.out.println("Is valid: "+textFieldBinder.isValid());
        });

        // Binder with integer
        FieldBinder<Integer> integerFieldBinder = new FieldBinder<>();
        TextField integerField = new TextField("Input number");

        // Text field with Integer value Converter and Validator
        // Demoing how to detect if value is valid and how to get it from FieldBinding
        FieldBinding<Integer> integerBinding = integerFieldBinder.forField(integerField)
        		.withConverter(new StringToIntegerConverter("This is not a number"))
        		.withValidator(new IntegerRangeValidator("Give a number between 5 and 10",5,10))
        		.bind(integerValue);
        integerFieldBinder.addValueChangeListener(event ->  {
        	System.out.println("Integer: "+integerValue);            
        	System.out.println("String from event via field: "+event.getValue());
        	System.out.println("Valid String from binding: "+integerBinding.getValue());
        	System.out.println("Is valid: "+integerFieldBinder.isValid());
        });

        // Text field with Double value Converter and Validator
        // Demoing how to detect if value is valid, showing customized error using status handler
        FieldBinder<Double> doubleFieldBinder = new FieldBinder<>();
        TextField doubleField = new TextField("Input double");       
        doubleFieldBinder.forField(doubleField)
        		.withConverter(new StringToDoubleConverter("This is not a number"))
        		.withValidator(new DoubleRangeValidator("Give a number between -10 and 10",-10.0d,10.0d))
        		.withValidationStatusHandler(status -> { 
        			status.getMessage().ifPresent(message -> Notification.show(message,Type.ERROR_MESSAGE));
        			if (!status.isError()) doubleValue = (Double) status.getBinding().getValue();
        			System.out.println("New valid double: "+doubleValue);
        			})
        		.bind(doubleValue);

        // Text field with Date value Validator against Field type and Converter to model type
        // Demoing how to show validation status in custom label
        HorizontalLayout dateLayout = new HorizontalLayout();
        FieldBinder<Date> dateFieldBinder = new FieldBinder<>();
        DateField dateField = new DateField("Select date");
        Label dateLabel = new Label();
        dateLabel.setContentMode(ContentMode.HTML);
        dateLayout.addComponents(dateLabel,dateField);
        dateLayout.setComponentAlignment(dateLabel, Alignment.MIDDLE_CENTER);
        dateLayout.setComponentAlignment(dateField, Alignment.MIDDLE_CENTER);
        
        dateFieldBinder.forField(dateField)
				.withValidator(new DateRangeValidator("<B>Date cant be from the past</B>",LocalDate.now(),LocalDate.MAX))
        		.withConverter(new LocalDateToDateConverter())
        		.withStatusLabel(dateLabel)
        		.bind(dateValue);
        
        // Show it in the middle of the screen
        VerticalLayout layout = new VerticalLayout();
        layout.setStyleName("demoContentLayout");
        layout.setSizeFull();
        layout.setMargin(false);
        layout.setSpacing(false);
        layout.addComponents(textField,integerField,doubleField,dateLayout);
        layout.setComponentAlignment(textField, Alignment.MIDDLE_CENTER);
        layout.setComponentAlignment(integerField, Alignment.MIDDLE_CENTER);
        layout.setComponentAlignment(doubleField, Alignment.MIDDLE_CENTER);
        layout.setComponentAlignment(dateLayout, Alignment.MIDDLE_CENTER);
        setContent(layout);
    }
}
