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
    String NULL = null;
    String ONE_STRING = "1"
    def ZERO = 0;
    def ONE = 1;
    def TWO = 2;
    def THREE = 3;
    def FOUR = 4;
    def FIVE = 5;
    def SIX = 6;
    def SEVEN = 7;
    def TWELVE = 12;
    def EIGHTEEN = 18;
    def TWENTY_SIX = 26
    def TWENTY_THREE = 23
    def THIRTY = 30;
    def THIRTY_NINE = 39;
    def FIFTY = 50;
    def SIXTY = 60;
    def SIXTY_THREE = 63;
    def HUNDRED = 100;
    def FOURTEEN_POINT_THREE_TWO = "14.32"
    String GEL = "gel.omicia.com"
    String NORMAL_USER = "Normal"
    String ADMIN = "Admin"
    String OWNER = "Owner"
    String SAVE = "Save"
    String CANCEL = "Cancel"
    String ACTIONS = "Actions"
    String DELETE = "Delete"
    String EDIT = "Edit"
    String CHROMOSOME = "Chromosome"

    /*Home Page*/
    String UPLOAD_GENOMES = "Upload Genomes";
    String UPLOAD = "Upload";
    String PROJECTS = "Projects";
    String PANEL_BUILDER = "Panel Builder";
    String CLINICAL_REPORTER = "Clinical Reporter";
    String CONDITION_GENE_ASSOCIATIONS = "Condition Gene Associations";
    String FILTERING_PROTOCOL = "Filtering Protocols";
    String GENE_SETS = "Gene Sets";
    String APP_STORE = "App Store";
    String ASSAY_TYPES = "Assay Types";
    String ACMG_AUTOMATION_WORKSPACE = "ACMG_Automation_Workspace_"
    String MANAGE_WORKSPACES = "Manage Workspaces"
    String ID = "id"
    String NAME = "Name"
    String USERS = "Users"

    /*Projects Page*/
    String CLINICAL_GRADE = ".clinical-grade";
    String UPLOADED_ON = ".uploaded-on";
    String REPORT_TYPE = ".report-type a";
    String REPORT_DATE = ".report-date";
    String REPORT_VERSION = ".report-version";
    String REPORT_STATUS = ".report-status";

    String VAAST_SOLO_ANALYSIS = "VAAST Solo Analysis"
    String VAAST_TRIO_ANALYSIS = "VAAST Trio Analysis"
    String VAAST_QUAD_ANALYSIS = "VAAST Quad Analysis"
    String FLEX_TRIO_ANALYSIS = "Flex Trio Analysis"
    String FLEX_QUAD_ANALYSIS = "Flex Quad Analysis"
    String VAAST_SOLO_REPORT = "VAAST Solo Report"
    String VAAST_TRIO_REPORT = "VAAST Trio Report"
    String VAAST_QUAD_REPORT = "VAAST Quad Report"
    String FLEX_TRIO_REPORT = "Flex Trio Report"
    String FLEX_QUAD_REPORT = "Flex Quad Report"
    String VAAST_SCORE = "VAAST score"

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

    String PROTIEN_FILTER = "Protein Changing, in Coding Regions";
    String SAVE_PANEL = "save-panel";
    String RETURN_TO_PANELS = "navigate-back";

    String BY_SYMBOL = "symbols"
    String BY_GENE_SET = "sets"
    String FROM_OTHER_PANELS = "panels"
    String FROM_HPO_TERMS_AND_PHEVOR = "phevor"
    String FROM_CHROMOSOME_REGIONS = "regions"
    String ATAXIA = "Ataxia"
    String FEVER = "Fever"
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

    public GENE_OF_AFFECTED_PERSON = "Genome-Label-1-EX024-1 — External-ID-1 (Pipeline Version:"
    public GENE_OF_UNAFFECTED_FATHER = "Genome-Label-1-EX024-4 — External-ID-1 (Pipeline Version:"
    public GENE_OF_UNAFFECTED_MOTHER = "Genome-Label-1-EX024-3 — External-ID-1 (Pipeline Version:"
    public GENE_OF_UNAFFECTED_SIBLING = "Genome-Label-1-EX024-2 — External-ID-1 (Pipeline Version:"

    public String EDIT_PATIENT_INFORMATION = "Edit Patient Information"
    public String INTERPRET_VARIANTS = "Interpret Variants"
    public String REPORT_HISTORY = "Report History"
    public String EXPORT_VARIANTS_TO_BE_CONFIRMED = "Export Variants To Be Confirmed "
    public String ASSIGN = "Assign"
    public String DELETE_REPORT = "Delete Report"

    public String MISSENSE = "missense"
    public String SYNONYMOUS = "synonymous"
    public String SAMD11 = "SAMD11"
    public String PLEKHN1 = "PLEKHN1"
    public String SCNN1D = "SCNN1D"
    public String TTLL10 = "TTLL10"
    public String AGRN = "AGRN"

    public String WAITING_FOR_APPROVAL = "Waiting for Approval"
    public String APPROVED = "Approved"

    /*Variant Interpret Home Page*/
    public List PRE_ACMG_SOLO_COLUMN_NAMES_IN_VARIANT_INTERPRETATION_PAGE = ["Review Priority", "Gene","Position dbSNP", "Change", "Effect", "Zygosity", "Quality GQ Coverage", "1KG AF EVS AF ExAC AF", "Omicia Score", "Evidence", "Class", "VAAST gene rank", "Phevor gene rank", "Inheritance Mode", "Status", "Previously Seen"].sort()
    public List PRE_ACMG_TRIO_COLUMN_NAMES_IN_VARIANT_INTERPRETATION_PAGE = ["Review Priority", "Gene","Position dbSNP", "Change", "Effect", "Zygosity", "Mother Zygosity", "Father Zygosity", "Quality GQ Coverage", "1KG AF EVS AF ExAC AF", "Omicia Score", "Evidence", "Class", "VAAST gene rank", "Phevor gene rank", "Inheritance Mode", "Status", "Previously Seen"].sort()
    public List PRE_ACMG_QUAD_COLUMN_NAMES_IN_VARIANT_INTERPRETATION_PAGE = ["Review Priority", "Gene","Position dbSNP", "Change", "Effect", "Zygosity", "Mother Zygosity", "Father Zygosity", "Sibling 1 Zygosity", "Quality GQ Coverage", "1KG AF EVS AF ExAC AF", "Omicia Score", "Evidence", "Class", "VAAST gene rank", "Phevor gene rank", "Inheritance Mode", "Status", "Previously Seen"].sort()
    public List PRE_ACMG_GEL_SOLO_COLUMN_NAMES_IN_VARIANT_INTERPRETATION_PAGE = ["Review Priority", "Gene","Position dbSNP", "Change", "Effect", "Zygosity", "Quality GQ Coverage", "1KG AF GeL AF ExAC AF", "Omicia Score", "Evidence", "Class", "VAAST gene rank", "Phevor gene rank", "Inheritance Mode", "Status", "Previously Seen"].sort()
    public List PRE_ACMG_GEL_TRIO_COLUMN_NAMES_IN_VARIANT_INTERPRETATION_PAGE = ["Review Priority", "Gene","Position dbSNP","Change", "Effect", "Zygosity", "Mother Zygosity", "Father Zygosity", "Quality GQ Coverage", "1KG AF GeL AF ExAC AF", "Omicia Score", "Evidence", "Class", "VAAST gene rank", "Phevor gene rank", "Inheritance Mode", "Status", "Previously Seen"].sort()
    public List PRE_ACMG_GEL_QUAD_COLUMN_NAMES_IN_VARIANT_INTERPRETATION_PAGE = ["Review Priority", "Gene","Position dbSNP","Change", "Effect", "Zygosity", "Mother Zygosity", "Father Zygosity", "Sibling 1 Zygosity", "Quality GQ Coverage", "1KG AF GeL AF ExAC AF", "Omicia Score", "Evidence", "Class", "VAAST gene rank", "Phevor gene rank", "Inheritance Mode", "Status", "Previously Seen"].sort()

    public List ACMG_QUAD_COLUMN_NAMES_IN_VARIANT_INTERPRETATION_PAGE = ["Review Priority", "Gene","Position dbSNP", "Change", "Effect", "Zygosity", "Mother Zygosity", "Father Zygosity", "Sibling 1 Zygosity", "Quality GQ Coverage", "1KG AF EVS AF ExAC AF", "Omicia Score", "Evidence", "Class (Condition)", "VAAST gene rank","Phevor gene rank", "Inheritance Mode", "Scoring Status", "Report Section","Latest Classification (Date Classified) Confirmation Status"].sort()

    public String RECESSIVE = "Recessive"
    public String DOMINANT = "Dominant"
    public String DE_NOVO = "De Novo"
    public String SCORING = "Scoring"
    public String CLASSIFIED = "Classified"
    public String SCORING_IN_PROGRESS = "Scoring in Progress"
    public String NOT_REPORTED = "Not Reported"

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
    public List PRE_ACMG_PANEL_COLUMN_NAMES_IN_VARIANT_SELECTION_PAGE = ["Review Priority", "Gene", "Position dbSNP", "Change", "Effect", "Zygosity", "Quality GQ Coverage", "1KG AF EVS AF ExAC AF", "Omicia Score", "Evidence", "Class", "Status", "Previously Seen"].sort()
    public List PRE_ACMG_SOLO_COLUMN_NAMES_IN_VARIANT_SELECTION_PAGE = ["Review Priority", "Gene", "Position dbSNP", "Change", "Effect", "Zygosity", "Quality GQ Coverage", "1KG AF EVS AF ExAC AF", "Omicia Score", "Evidence", "VAAST gene rank", "Phevor gene rank"].sort()
    public List PRE_ACMG_TRIO_COLUMN_NAMES_IN_VARIANT_SELECTION_PAGE = ["Review Priority", "Gene", "Position dbSNP", "Change", "Effect", "Zygosity", "Mother Zygosity", "Father Zygosity", "Quality GQ Coverage", "1KG AF EVS AF ExAC AF", "Omicia Score", "Evidence", "VAAST gene rank", "Phevor gene rank"].sort()
    public List PRE_ACMG_QUAD_COLUMN_NAMES_IN_VARIANT_SELECTION_PAGE = ["Review Priority", "Gene", "Position dbSNP", "Change", "Effect", "Zygosity", "Mother Zygosity", "Father Zygosity", "Sibling 1 Zygosity", "Quality GQ Coverage", "1KG AF EVS AF ExAC AF", "Omicia Score", "Evidence", "VAAST gene rank", "Phevor gene rank"].sort()
    public List PRE_ACMG_GEL_PANEL_COLUMN_NAMES_IN_VARIANT_SELECTION_PAGE = ["Review Priority", "Gene","Position dbSNP",  "Change", "Effect", "Zygosity", "Quality GQ Coverage", "1KG AF GeL AF ExAC AF", "Omicia Score", "Evidence", "Class", "Status", "Previously Seen"].sort()
    public List PRE_ACMG_GEL_SOLO_COLUMN_NAMES_IN_VARIANT_SELECTION_PAGE = ["Review Priority", "Gene","Position dbSNP",  "Change", "Effect", "Zygosity", "Quality GQ Coverage", "1KG AF GeL AF ExAC AF", "Omicia Score", "Evidence", "VAAST gene rank", "Phevor gene rank"].sort()
    public List PRE_ACMG_GEL_TRIO_COLUMN_NAMES_IN_VARIANT_SELECTION_PAGE = ["Review Priority", "Gene","Position dbSNP",  "Change", "Effect", "Zygosity", "Mother Zygosity", "Father Zygosity", "Quality GQ Coverage", "1KG AF GeL AF ExAC AF", "Omicia Score", "Evidence", "VAAST gene rank", "Phevor gene rank"].sort()
    public List PRE_ACMG_GEL_QUAD_COLUMN_NAMES_IN_VARIANT_SELECTION_PAGE = ["Review Priority", "Gene","Position dbSNP",  "Change", "Effect", "Zygosity", "Mother Zygosity", "Father Zygosity", "Sibling 1 Zygosity", "Quality GQ Coverage", "1KG AF GeL AF ExAC AF", "Omicia Score", "Evidence", "VAAST gene rank", "Phevor gene rank"].sort()

    public List ACMG_PANEL_COLUMN_NAMES_IN_VARIANT_SELECTION_PAGE = ["Review Priority", "Position dbSNP", "Gene", "Change", "Effect", "Zygosity", "Quality GQ Coverage", "1KG AF EVS AF ExAC AF", "Omicia Score", "Evidence", "Class (Condition)", "Scoring Status", "Report Section", "Latest Classification (Date Classified) Confirmation Status"].sort()
    public List ACMG_GEL_PANEL_COLUMN_NAMES_IN_VARIANT_SELECTION_PAGE = ["Review Priority", "Position dbSNP", "Gene", "Change", "Effect", "Zygosity", "Quality GQ Coverage", "1KG AF GeL AF ExAC AF", "Omicia Score", "Evidence", "Class (Condition)", "Scoring Status", "Report Section", "Latest Classification (Date Classified) Confirmation Status"].sort()
    public List ACMG_QUAD_COLUMN_NAMES_IN_VARIANT_SELECTION_PAGE = ["Review Priority", "Gene","Position dbSNP",  "Change", "Effect", "Zygosity", "Mother Zygosity", "Father Zygosity","Sibling 1 Zygosity","Quality GQ Coverage", "1KG AF EVS AF ExAC AF", "Omicia Score", "Evidence","VAAST gene rank","Phevor gene rank","Latest Classification (Date Classified) Confirmation Status"].sort()
    public List ACMG_QUAD_GEL_COLUMN_NAMES_IN_VARIANT_SELECTION_PAGE = ["Review Priority", "Gene","Position dbSNP",  "Change", "Effect", "Zygosity", "Mother Zygosity", "Father Zygosity","Sibling 1 Zygosity","Quality GQ Coverage", "1KG AF EVS AF ExAC AF", "Omicia Score", "Evidence", "Class (Condition)", "VAAST gene rank","Phevor gene rank","Inheritance Mode", "Scoring Status", "Report Section", "Latest Classification (Date Classified) Confirmation Status"].sort()
    String HOMOZYGOUS = "●●"
    String HETROZYGOUS = "●○"

    /*Show Hide Columns*/
    public String TO_REPORT = "To Report"

    /*Variant Report Page*/
    public List VAAST_SOLO_REPORT_COLUMN_LIST = ["Review Priority", "Gene", "Position dbSNP", "Change", "Effect", "Zygosity", "Quality GQ Coverage", "1KG AF EVS AF ExAC AF", "Omicia Score", "VVP CADD", "Evidence", "VAAST Rank", "VAAST V-Score", "VAAST G-Score"].sort()
    public List VAAST_TRIO_REPORT_COLUMN_LIST = ["Review Priority", "Gene", "Position dbSNP", "Change", "Effect", "Zygosity", "Mother Zygosity", "Father Zygosity", "Quality GQ Coverage", "1KG AF EVS AF ExAC AF", "Omicia Score", "VVP CADD", "Evidence", "VAAST Rank", "VAAST V-Score", "VAAST G-Score"].sort()
    public List VAAST_QUAD_REPORT_COLUMN_LIST = ["Review Priority", "Gene", "Position dbSNP", "Change", "Effect", "Zygosity", "Mother Zygosity", "Father Zygosity", "Sibling Zygosity", "Quality GQ Coverage", "1KG AF EVS AF ExAC AF", "Omicia Score", "VVP CADD", "Evidence", "VAAST Rank", "VAAST V-Score", "VAAST G-Score"].sort()
    public List FLEX_TRIO_REPORT_COLUMN_LIST = ["Review Priority", "Gene", "Position dbSNP", "Change", "Effect", "Zygosity", "Mother Zygosity", "Father Zygosity", "Quality GQ Coverage", "1KG AF EVS AF ExAC AF", "Omicia Score", "VVP CADD", "Evidence"].sort()
    public List FLEX_QUAD_REPORT_COLUMN_LIST = ["Review Priority", "Gene", "Position dbSNP", "Change", "Effect", "Zygosity", "Mother Zygosity", "Father Zygosity", "Sibling Zygosity", "Quality GQ Coverage", "1KG AF EVS AF ExAC AF", "Omicia Score", "VVP CADD", "Evidence"].sort()
    public List VAAST_SOLO_REPORT_COLUMN_LIST_GEL = ["Review Priority", "Gene", "Position dbSNP", "Change", "Effect", "Zygosity", "Quality GQ Coverage", "1KG AF GeL AF ExAC AF", "Omicia Score", "VVP CADD", "Evidence", "VAAST Rank", "VAAST V-Score", "VAAST G-Score"].sort()
    public List VAAST_TRIO_REPORT_COLUMN_LIST_GEL = ["Review Priority", "Gene", "Position dbSNP", "Change", "Effect", "Zygosity", "Mother Zygosity", "Father Zygosity", "Quality GQ Coverage", "1KG AF GeL AF ExAC AF", "Omicia Score", "VVP CADD", "Evidence", "VAAST Rank", "VAAST V-Score", "VAAST G-Score"].sort()
    public List VAAST_QUAD_REPORT_COLUMN_LIST_GEL = ["Review Priority", "Gene", "Position dbSNP", "Change", "Effect", "Zygosity", "Mother Zygosity", "Father Zygosity", "Sibling Zygosity", "Quality GQ Coverage", "1KG AF GeL AF ExAC AF", "Omicia Score", "VVP CADD", "Evidence", "VAAST Rank", "VAAST V-Score", "VAAST G-Score"].sort()
    public List FLEX_TRIO_REPORT_COLUMN_LIST_GEL = ["Review Priority", "Gene", "Position dbSNP", "Change", "Effect", "Zygosity", "Mother Zygosity", "Father Zygosity", "Quality GQ Coverage", "1KG AF GeL AF ExAC AF", "Omicia Score", "VVP CADD", "Evidence"].sort()
    public List FLEX_QUAD_REPORT_COLUMN_LIST_GEL = ["Review Priority", "Gene", "Position dbSNP", "Change", "Effect", "Zygosity", "Mother Zygosity", "Father Zygosity", "Sibling Zygosity", "Quality GQ Coverage", "1KG AF GeL AF ExAC AF", "Omicia Score", "VVP CADD", "Evidence"].sort()

    public String VAAST_VIEWER = "VAAST Viewer"
    public String RESET_FILTERS = "Reset Filters"
    public String EXPORT_REPORT = "Export Report"
    public String VAAST_VIEWER_REQUEST_APPENDER = "/variants/export?inheritance=RECESSIVE&filter=%7B%22description%22%3Anull%2C%22quality%22%3A%7B%22min%22%3Anull%2C%22max%22%3Anull%7D%2C%22coverage%22%3A%7B%22min%22%3Anull%2C%22max%22%3Anull%7D%2C%22frequency%22%3A%7B%22min%22%3Anull%2C%22max%22%3Anull%7D%2C%22evsfrequency%22%3A%7B%22min%22%3Anull%2C%22max%22%3Anull%7D%2C%22exacfrequency%22%3A%7B%22min%22%3Anull%2C%22max%22%3Anull%7D%2C%22sift%22%3A%7B%22min%22%3Anull%2C%22max%22%3Anull%7D%2C%22omiscore%22%3A%7B%22min%22%3Anull%2C%22max%22%3Anull%7D%2C%22chrom%22%3Anull%2C%22zygosity%22%3A%22%22%2C%22mutationtype%22%3A%22%22%2C%22regulatory%22%3Afalse%2C%22refseq%22%3Afalse%2C%22ccds%22%3Afalse%2C%22in-report%22%3Afalse%2C%22dbsnp%22%3Afalse%2C%22nocall%22%3Atrue%2C%22intronic%22%3Afalse%2C%22intergenic%22%3Afalse%2C%22filterFail%22%3Atrue%2C%22filterNone%22%3Afalse%2C%22noncoding%22%3Atrue%2C%22polymorph%22%3Afalse%2C%22omimhits%22%3Afalse%2C%22clinvarhits%22%3Afalse%2C%22allhits%22%3Afalse%2C%22consequence%22%3A%22nonsynonymous%22%2C%22chromStart%22%3Anull%2C%22chromEnd%22%3Anull%2C%22genesymbol%22%3A%5B%5D%2C%22rsid%22%3A%5B%5D%2C%22abratio%22%3Anull%2C%22panel%22%3A%22%22%2C%22gq%22%3A%7B%22min%22%3Anull%2C%22max%22%3Anull%7D%2C%22vvpscore%22%3A%7B%22min%22%3Anull%2C%22max%22%3Anull%7D%2C%22cadd%22%3A%7B%22min%22%3Anull%2C%22max%22%3Anull%7D%2C%22include_gene_sets%22%3A%5B%5D%2C%22exclude_gene_sets%22%3A%5B%5D%2C%22kp%22%3Afalse%2C%22lp%22%3Afalse%2C%22vus%22%3Afalse%2C%22benign%22%3Afalse%2C%22likely_benign%22%3Afalse%2C%22assoc%22%3Afalse%2C%22no_class%22%3Afalse%2C%22unique_variants%22%3A%7B%22genomes%22%3A%7B%7D%2C%22match_allele%22%3Atrue%2C%22match_zygosity%22%3Atrue%7D%2C%22shared_variants%22%3A%7B%22genomes%22%3A%7B%7D%2C%22match_allele%22%3Atrue%2C%22match_zygosity%22%3Atrue%7D%7D"

    /*Scoring Variants*/
    public String SCORE_VARIANT = "Score Variant"
    public String GENE_KNOWLEDGE_BASE = "Gene Knowledge Base"
    public String SCORING_HISTORY = "Scoring History"
    public String CITATIONS = "Citations"
    public String CONDITION_GENE = "Condition-Gene"

    public String WORKSPACE_CONDITION_GENES = "Workspace Condition-Genes"
    public String CLINIVAR_OMIM = "ClinVar and OMIM Condition-Genes"
    public String NLP_PHENOTYPE = "NLP Phenotype Mapper"

    public String NLP_PHENOTYPE_COLUMN_NAMES = "Condition p-Value Score Citations"
    public String CLINVAR_OMIM_COLUMN_NAMES = "Condition Inheritance Prevalence Age of Onset Additional Information Source Actions"

    public String CLINVAR_OMIM_CONDITION_NAME = "MYASTHENIC SYNDROME, CONGENITAL, WITH PRE- AND POSTSYNAPTIC DEFECTS"
    public String COPY_TO_WORKSPACE = "Copy to Workspace"
    public PREVALANCE_VALUE = "1-9 / 1 000 000"
    public String NEONATAL = "Neonatal"

    public String VARIANT_DESCRIPTION = "Variant Description"
    public String SCORING_SUMMARY = "Scoring Summary"
    public String INTERNAL_NOTES = "Internal Notes"
    public String VARIANT_HISTORY = "Variant History"
    public String UNSCORED = "Unscored"
    public String BENIGN = "Benign"

    public String PRIMARY_FINDINGS = "Primary Findings"
    public String SECONDARY_FINDINGS = "Secondary Findings"

    /*Citations*/
    public String COSEGREGATION = "cosegregation"
    public String FUNCTIONAL = "functional"
    public String GENERAL = "general"

    /*Score Variant*/
    public String YES = "Yes"
    public String NO = "No"
    public String STRONG = "Strong"
    public String SKIP_CRITERIA = "Skip"
    public String CLEAR_SELECTION = "Clear Selection"

    /*Manage WorkSpaces*/
    public String ADMINS = "Admins"
    public String GROUPS = "Groups"
    public String PAYMENT_INFO = "Payment Info"
    public String CLINICAL_REPORTS = "Clinical Reports"
    public String MEMBERS = "Members"

    /*Group Tab*/
    public String CLINICAL_REPORTER_ACCESS = "Clinical Reporter access"

    /*Projects Home Page*/
    public String PROJECT_DESCRIPTION_1 = "Sample Project Description 1"
    public String CREATE_ACTION = "Create Project"
    public String INVALID_SEARCH_STRING = "qwerty"
    public String VALID_SEARCH_STRING = "4"
    public String EDIT_PROJECT_OPTION = "Edit Project"
    public String PROJECT_DETAILS_OPTION = "Project Details"
    public String SHARE_PROJECT_OPTION = "Share Project"
    public String EDIT_PROJECT_NAME = "Test Project 1"
    public String EDIT_PROJECT_DESCRIPTION = "Sample Edit 1"
    public String PUBLIC_PROJECTS = "Public Projects"
    public String WORKSPACE_PROJECTS = "Workspace Projects"
    public List USERNAMES = ["Omicia Admin Owner"]
    public String STRING_USERNAME = "Omicia Admin Owner"
    public String USERLOGINEMAIL = "omicia.test.user+owner@gmail.com"
    public String PROJECTROLE_CONTRIBUTOR = "Contributor"
    public String PROJECTROLE_VIEWER = "Viewer"
    public String REMOVEME_OPTION = "Remove Me"
    public String REMOVEPROJECT_BUTTON = "Remove Project"
    public String SAVE_CHANGES = "Save Changes"
    public String PRE_ACMG_WORKSPACE = "Pre_ACMG_Automation_Workspace_Omicia"
    public List ACTIONSLIST_VIEWER = ["Project Details", "Remove Me"]
    public List ACTIONSLIST_CONTRIBUTOR = ["Project Details", "Edit Project", "Share Project", "Upload Genomes"]
    public int WID = 300
}
