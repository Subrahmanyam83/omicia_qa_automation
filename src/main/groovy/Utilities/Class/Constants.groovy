package Utilities.Class

import com.relevantcodes.extentreports.LogStatus

/**
 * Created by E002183 on 4/25/2016.
 */
public interface Constants {

    /*Extent Reports*/
    LogStatus PASS = LogStatus.PASS;
    LogStatus FAIL = LogStatus.FAIL;
    LogStatus SKIP = LogStatus.SKIP;
    LogStatus INFO = LogStatus.INFO;
    LogStatus WARNING = LogStatus.WARNING;
    LogStatus FATAL = LogStatus.FATAL;
    LogStatus UNKNOWN = LogStatus.UNKNOWN;
    LogStatus ERROR = LogStatus.ERROR;

    /*General*/
    String CLICK = "click";
    String GET_TEXT = "get_text";
    int ZERO = 0;
    int ONE = 1;
    int TWO = 2;

    /*Home Page*/
    String UPLOAD_GENOMES = "Upload Genomes";
    String PROJECTS = "Projects";
    String PANEL_BUILDER = "Panel Builder";
    String CLINICAL_REPORTER = "Clinical Reporter";
    String CONDITION_GENE_ASSOCIATIONS = "Condition Gene Associations";
    String FILTERING_PROTOCOL = "Filtering Protocols";
    String GENE_SETS = "Gene Sets";
    String APP_STORE = "App Store";
    String ASSAY_TYPES = "Assay Types";

    /*Projects Page*/
    String CLINICAL_GRADE = ".clinical-grade";
    String UPLOADED_ON = ".uploaded-on";
    String REPORT_TYPE = ".report-type a";
    String REPORT_DATE = ".report-date";
    String REPORT_VERSION = ".report-version";
    String REPORT_STATUS = ".report-status";

    /*Variant Report Page*/
    String POSITION = ".position a";
    String EFFECT = "a.consequence";
    String ONE_KG_AF = ".frequency-value";
    String EVIDENCE_CV = ".evidence.evidence-weak";

    /*Gene Sets*/
    String DISEASE_SET = "Disease Set";
    String DRUG_SET = "Drug Set";
    String PATHWAY_SET = "Pathway Set";
    String EXCLUDE_SET = "Exclude Set";
    String MY_SET = "My Set";
    String DELETE_GENE_SET = ".delete-bioset";
    String ADD_GENES = "add-genes";
    String REMOVE_GENES = ".remove-genes";
    String EDIT_GENE_SET = ".edit-bioset";

    /*Panel Builder*/
    String CURATE_PANEL = "curate-panel";
    String DELETE_PANEL = "delete-panel";

    String PROTIEN_FILTER = "Protein changing in coding regions";
    String SAVE_PANEL = "save-panel";
    String RETURN_TO_PANELS = "navigate-back";

    String BY_SYMBOL = "symbols"
    String BY_GENE_SET = "sets"
    String FROM_OTHER_PANELS = "panels"
    String FROM_HPO_TERMS_AND_PHEVOR ="phevor"
    String FROM_CHROMOSME_REGIONS = "regions"
    String RUN_PHEVOR_TEXT_VALUE = "Ataxia"
    String ADD_GENE = "Add Genes"
    String RUN_PHEVOR = "Run Phevor"
    String BACK = "Back"


}
