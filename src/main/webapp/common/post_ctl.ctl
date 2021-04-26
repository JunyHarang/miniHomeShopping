load data
infile 'newPost.csv'
insert into table postcodes
FIELDS TERMINATED BY ','
TRAILING NULLCOLS(
    area_cd, si_nm, sgg_nm, emd_nm, ri_nm, rd_nm, udrgrnd_yn, mt_yn, bd_ma_sn, bd_sb_sn, 
    lndn_ma_sn, lndn_sb_sn, search_word, display_word, display_word_dtail
)