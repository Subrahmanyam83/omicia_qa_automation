package Utilities.Class

import com.relevantcodes.extentreports.LogStatus

/**
 * Created by E002183 on 4/25/2016.
 */
public interface Constants {

    /*Upload Genomes*/
    public String PROJECT__NAME = "Test-OMICIA-Project-"
    public String SHORT_FILE = "/src/main/resources/VCFs/GID_validation_examples.vcf"
    public String FOUR_EXOMES = "/src/main/resources/VCFs/EX024_hc_first1000.vcf.gz"

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
    String TEST_NOTE = "Test Note"
    String CLICK = "click";
    String GET_TEXT = "get_text";
    String NONE = ""
    String NONE_TEXT = "None"
    int ZERO = 0;
    int ONE = 1;
    int TWO = 2;
    int THREE = 3;
    int FOUR = 4;
    int FIFTY = 50;
    int THIRTY = 30;
    int THIRTY_NINE = 39
    int SIXTY_THREE = 63;

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
    String FROM_HPO_TERMS_AND_PHEVOR = "phevor"
    String FROM_CHROMOSOME_REGIONS = "regions"
    String ATAXIA = "Ataxia"
    String ADD_GENE = "Add Genes"
    String RUN_PHEVOR = "Run Phevor"
    String BACK = "Back"

    /*Filtering Protocol*/
    public List CONSEQUENCE_LIST = ["Stop Gained or Lost", "Missense", "Frameshift Indel", "In-frame Indel", "Splice Site", "Splice Region", "Start Lost or Retained", "Structural Variant"]
    public List EVIDENCE_LIST = ["No calls", "In intronic regions", "In intergenic regions", "In non-coding regions"]

    /*Clinical Reports*/
    public String PANEL = "Panel"
    public String PANEL_TRIO = "Panel Trio"
    public String SOLO = "Solo"
    public String DUO = "Duo"
    public String TRIO = "Trio"
    public String QUAD = "Quad"

    public List SOLO_LIST = ["Affected Person"].sort()
    public List TRIO_LIST = ["Affected Child", "Unaffected Father", "Unaffected Mother"].sort()
    public List QUAD_LIST = ["Unaffected Sibling", "Affected Child", "Unaffected Father", "Unaffected Mother"].sort()

    public String AFFECTED_PERSON = "Affected Person"
    public String AFFECTED_CHILD = "Affected Child"
    public String UNAFFECTED_FATHER = "Unaffected Father"
    public String UNAFFECTED_MOTHER = "Unaffected Mother"
    public String UNAFFECTED_SIBLING = "Unaffected Sibling"

    public GENE_OF_AFFECTED_PERSON = "Genome-Label-1-EX024-1 — External-ID-1 (Pipeline Version: 6.0.1)"
    public GENE_OF_UNAFFECTED_FATHER = "Genome-Label-1-EX024-4 — External-ID-1 (Pipeline Version: 6.0.1)"
    public GENE_OF_UNAFFECTED_MOTHER = "Genome-Label-1-EX024-3 — External-ID-1 (Pipeline Version: 6.0.1)"
    public GENE_OF_UNAFFECTED_SIBLING = "Genome-Label-1-EX024-2 — External-ID-1 (Pipeline Version: 6.0.1)"

    public String EDIT_PATIENT_INFORMATION = "Edit Patient Information"
    public String INTERPRET_VARIANTS = "Interpret Variants"
    public String REPORT_HISTORY = "Report History"
    public String EXPORT_VARIANTS_TO_BE_CONFIRMED = "Export Variants To Be Confirmed "
    public String ASSIGN = "Assign"
    public String DELETE_REPORT = "Delete Report"

    public String MISSENSE = "missense"
    public String SAMD11 = "SAMD11"
    public String PLEKHN1 = "PLEKHN1"
    public String SCNN1D = "SCNN1D"

    /*Variant Interpret Home Page*/
    public List SOLO_COLUMN_NAMES_IN_VARIANT_INTERPRETATION_PAGE = ["Review Priority", "Gene", "Change", "Effect", "Zygosity", "Quality GQ Coverage", "1KG AF EVS AF ExAC AF", "Omicia Score", "Evidence", "Class", "VAAST gene rank", "Phevor gene rank", "Inheritance Mode", "Status", "Previously Seen"].sort()
    public String RECESSIVE = "Recessive"

    /*Variant Interpret Edit Page*/
    public String CLASSIFICATION_PATHOGENIC = "Pathogenic"
    public String PRIMARY_FINDING = "Primary finding"
    public String SECONDARY_FINDING = "Secondary finding"
    public String REVIEWED = "Reviewed"
    public String PRIMARY = "Primary"
    public String SECONDARY = "Secondary"

    public String EDIT_VARIANT = "Edit Variant"
    public String VARIANT_EVIDENCE = "Variant Evidence"
    public String UNCERTAIN_SIGNIFICANCE = "Uncertain Significance"
    public String DUCTAL_BREAST_CARCINOMA = "Ductal breast carcinoma"

    /*Variant Selection*/
    public List SOLO_COLUMN_NAMES_IN_VARIANT_SELECTION_PAGE = ["Review Priority", "Gene", "Change", "Effect", "Zygosity", "Quality GQ Coverage", "1KG AF EVS AF ExAC AF", "Omicia Score", "Evidence", "VAAST gene rank", "Phevor gene rank"].sort()

    /*Show Hide Columns*/
    public String TO_REPORT = "To Report"


}
