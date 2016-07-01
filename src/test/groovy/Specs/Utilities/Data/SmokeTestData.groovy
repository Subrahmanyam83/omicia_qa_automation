package Specs.Utilities.Data

import Utilities.Class.BaseSpec

/**
 * Created by E002183 on 4/25/2016.
 */
class SmokeTestData extends BaseSpec {

    public String random = "_" + generateRandom();

    public String GENE_LABEL = "Genome-Label-1 — External-ID-1"
    public String EXOME_GENE_LABEL = "Genome-Label-1-EX024-1 — External-ID-1"
    public String GENE_NAME = "CDK11A"
    public String GENE_SET_NAME = "Test Gene Set Name" + random
    public String DESCRIPTION = "Test Gene Set Description" + random
    public String GENE_SYMBOLS = "CDK11A ABCA4"
    public int GENE_COUNT = 5
    public int VARIANT_GENE = 3

    /*Panel Builder*/
    public String PANEL_NAME = "Test Panel Name" + random
    public String PANEL_DESCRIPTION = "Test panel Description" + random

    public List GENES_TO_BE_CHECKED = ["ATXN1", "AFG3L2"];
    public String GENES_TO_BE_ADDED_AS_SYMBOLS = "NOC2L PLEKHN1 SAMD11"

    /*Filtering protocol*/
    public String FILTERING_PROTOCOL_NAME = "Test Filtering Protocol Name" + random
    public String FILTERING_PROTOCOL_DESCRIPTION = "Test Filtering Protocol Description" + random

    /*Clinical Reporting*/
    public String PATIENT_ID = "Test Patient ID 123" + random
    public String GENOME_NAME_TO_BE_SELECTED = "Genome-Label-1-EX024-1 — External-ID-1"

    public String VARIANT_CHANGE_PANEL = "T → C c.1027T>C p.Trp343Arg"
    public String VARIANT_CHANGE_SOLO = "G → A c.869G>A p.Arg290His"
    public String VARIANT_CHANGE_TRIO = "T → C c.746T>C p.Val249Ala"
    public String VARIANT_CHANGE_QUAD = "T → C c.746T>C p.Val249Ala"
    public String VARIANT_EFFECT = "missense"

    public String VARIANT_CHANGE_PANEL_ACMG = "G → T c.45G>T p.Pro15Pro";
    public String VARIANT_CHANGE_QUAD_ACMG = "T → C c.746T>C p.Val249Ala";
    public String positiondbSNP_QUAD = "chr1:1116231 rs13374146";

    /*Report Page*/
    public String POSITION_DBSNP_VALUE = "chr1:1117779 rs553315851"
    public String CHANGE_VALUE = "G → A c.869G>A p.Arg290His"
    public String VVP_CADD_VALUE = "56 14"
    public String VAAST_G_SCORE_VALUE_RECESSIVE = "14.32 5.90e-1"
    public String VAAST_G_SCORE_VALUE_DOMINANT = "14.32 1.87e-1"

    /*Scoring Variants Page*/
    public List EDIT_CONDITION_GENE_LIST = [NONE, "Edit Condition Gene Note", "1", "X-linked", "950 /226 5906", "Medium", "Variable"]
    public List CREATE_CONDITION_GENE_LIST = ["Fever", "Edit Condition Gene Note", "2", "Autosomal unknown", "950 /226 5906", "Low", "Adolescence / Young adulthood"]
    public List CRITERION_SCORING_LIST = [YES,YES,YES,YES,YES,NO,NO,NO,NO,NO,NO,NO,NONE_TEXT,NO,NO,NO,NO,NO,NO,NO,SKIP_CRITERIA,SKIP_CRITERIA,NO,NO]
    public String CRITERION_COMPLETE_TEXT = "28 of 28"
    public String CRITERION_PROGRESS_TEXT = "28 of 28 complete"
}