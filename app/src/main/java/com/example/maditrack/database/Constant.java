package com.example.maditrack.database;

public class Constant {
    public static final String COLUMN_ID = "_id";
    public final static String COLUMN_NAME = "NAME";
    public static final String COLUMN_CREATED_TIME = "CREATEDTIME";
    public static final String PersonalBio_Table_Name = "PERSONALBIO";
    public static final String HealthBio_Table_Name = "HEALTHBIO";
    public static final String Categories_Table_Name = "CATEGORY";
    public static final String Medicine_Table_Name = "MEDICINE";
    public static final String Measurement_Table_Name = "MEASUREMENT";
    public static final String Consumption_Table_Name = "CONSUMPTION";
    public static final String Reminder_Table_Name = "REMINDER";
    public static final String Appointment_Table_Name = "APPOINTMENT";
    public static final String ICE_Table_Name = "ICE";
    public static final String SYSTOLIC = "SYSTOLIC";
    public static final String DATEOFBIRTH = "DATEOFBIRTH";
    public static final String UNIQUEID = "UNIQUEID";
    public static final String PERSONALADDRESS = "ADDRESS";
    public static final String POSTALCODE = "POSTALCODE";
    public static final String HEIGHT = "HEIGHT";
    public static final String BLOODTYPE = "BLOODTYPE";
    public static final String CONDITION = "CONDITION";
    public static final String CONDITIONTYPE = "CONDITIONTYPE";
    public static final String CATEGORYCODE = "CATEGORYCODE";
    public static final String DESCRIPTION = "DESCRIPTION";
    public static final String REMIND = "REMIND";
    public static final String CATEGORYID = "CATEGORYID";
    public static final String REMINDERID = "REMINDERID";
    public static final String QUANTITY = "QUANTITY";
    public static final String DOSAGE = "DOSAGE";
    public static final String DATEISSUES = "DATEISSUES";
    public static final String THRESHOLD = "THRESHOLD";
    public static final String DIASTOLIC = "DIASTOLIC";
    public static final String PULSE = "PULSE";
    public static final String TEMPERATURE = "TEMPERATURE";
    public static final String WEIGHT = "WEIGHT";
    public static final String MEASUREDON = "MEASUREDON";
    public static final String MEDICINEID = "MEDICINEID";
    public static final String CONSUMEDON = "CONSUMEDON";
    public static final String CONTACTNO = "CONTACTNO";
    public static final String CONTACTTYPE = "CONTACTTYPE";
    public static final String PREFERENCES = "PREFERENCES";
    public static final String LOCATION = "LOCATION";
    public static final String FREQUENCY = "FREQUENCY";
    public static final String INTERVAL = "INTERVAL";
    public static final String APPOINTMENTDATETIME = "APPOINTMENTDATETIME";
    public static final String APPOINTMENTDATE = "APPOINTMENTDATE";
    public static final String APPOINTMENTTIME = "APPOINTMENTTIME";
    public static final String STARTTIME = "STARTTIME";
    public static final String STARTDATE = "STARTDATE";
    public static final String REMINDERDATETIME = "REMINDERDATETIME";


    //GOVARDHAN WORK
    public static final String TableName = "categorytable";

    public static final String MedicineTableName = "medicinetablecategory";

    public static final String CategoryId = "_id";

    public static final String Categoryname = "categoryname";

    public static final String CategoryCode = "categorycode";

    public static final String CategoryDescription = "categorydescription";

    public static final String CategoryReminder = "categoryreminder";

    public static final String Categorynamevalue = "SUPPLEMENT";

    public static final String CategoryCodevalue = "SUP";

    public static final String CategoryDescriptionvalue = "User may opt to set reminder for consumption of supplement.";

    public static final String Categorynamevalue2 = "CHRONIC";

    public static final String CategoryCodevalue2 = "CHR";

    public static final String CategoryDescriptionvalue2 = "This is to categorise medicstion for long-term/life-time consumtion for diseases,i.e. diabetes, hypertension,heart regulation,etc.";

    public static final String Categorynamevalue3 = "INCIDENTAL";

    public static final String CategoryCodevalue3 = "INC";

    public static final String CategoryDescriptionvalue3 = "For common cold,flu or symptoms happen to be unplanned or subordinate conjuction with something and prescription from general practitioners.";

    public static final String Categorynamevalue4 = "COMPLETE COURSE";

    public static final String CategoryCodevalue4 = "COM";

    public static final String CategoryDescriptionvalue4 = "This may applies to medication like antibiotics for sinus infection,pneumonia,bronchiis,acne,strep throat,cellulitis,etc.";

    public static final String Categorynamevalue5 = "SELF APPLY";

    public static final String CategoryCodevalue5 = "SEL";

    public static final String CategoryDescriptionvalue5 = "To note down any self-prescribed or consume medication,i.e pplying band aids,balms,etc";

    public static final String MedicineId = "Medicineid";

    public static final String MedicineName = "Medicinename";

    public static final String MedicineDescription = "Medicinedescription";

    public static final String MedicineCatId = "Medicinecatid";

    public static final String MedicineRemenderId = "MedicinereminderId";

    public static final String MedicineRemind = "Medicineremind";

    public static final String MedicineQuantity = "Medicinequantity";

    public static final String MedicineDosage = "Medicinedosage";

    public static final String MedicineDateIssued = "Medicinedateissued";

    public static final String MedicineConsumeQuantity = "Mediciconsumequantity";

    public static final String MedicineThreshold = "Medicinethreshold";

    public static final String MedicineExpireFactor = "Medicineexpirefactor";

    public static final String ErrorMsg_PleaseEnterLocation = "Please enter location";
    public static final String ErrorMsg_PleaseEnterDescription = "Please enter description";
    public static final String ErrorMsg_PleaseEnterDate = "Please enter date";
    public static final String ErrorMsg_PleaseEnterTime = "Please enter time";
    public static final String ErrorMsg_RecordNotUpdated = "Record not updated";
    public static final String ErrorMsg_RecordNotAdded = "Record not added";
    public static final String ErrorMsg_DateParseError = "Date parse error";


    public static final String NotificationMsg_AppointmentAdded = "Appointment added and reminder set successfully";
    public static final String NotificationMsg_AppointmentUpdated = "Appointment Updated Successfully";
    public static final String NotificationMsg_AppointmentDeleted = "Appointment Deleted Successfully";
    public static  final String MESSAGE ="MESSAGE";
    public static final String APPOINTMENT_REMINDER_MESSAGE = "Attention you have an appointment today";
    public static final String MEDICINE_REMINDER_MESSAGE =  "It's time to take medicine";
    public static final String NOTIFICATION_MESSAGE =  "New notification";


    public static final String NotificationMsg_ContactAdded = "Contact Added Successfully";
    public static final String NotificationMsg_ContactUpdated = "Contact Updated Successfully";
    public static final String NotificationMsg_ContactDeleted = "Contact Deleted Successfully";

    public static final String AppointmentSelectQuery =  "Select * from Appointment";


    public static final String NotificationMsg_HealthBioAdded = "HealthBio Added Successfully";
    public static final String NotificationMsg_HealthBioUpdated = "HealthBio Updated Successfully";
    public static final String NotificationMsg_HealthBioDeleted = "HealthBio Deleted Successfully";
    public static final String ErrorMsg_pleaseenter_condtion = "Please Enter Condition";

    public static final String HealthBioSelectQuery = "Select * from HealthBio";
}
