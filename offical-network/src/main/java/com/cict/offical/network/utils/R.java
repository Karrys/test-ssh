package com.cict.offical.network.utils;

public final class R {

	public static final class SWAGGER {
		public static final String BASEPACKAGE = "com.cict.offical.network.controller";
		public static final String TITLE = "RESTful APIs";
		public static final String VERSION = "1.0";
	}
	
	/**
	 * 管理员用户
	 */
	public static final String ADMIN = "ADMIN";
	
	/**
	 * 招聘信息
	 */
	public static final class RECRUIT_INFO {
		/**
		 * 职位状态  0:待发布，1：已发布，2：已下线
		 */
		public static final class STATE {
			/**
			 * 0:待发布
			 */
			public static final Integer TO_BE_RELEASED = 0;
			/**
			 * 1：已发布 
			 */
			public static final Integer RELEASED = 1;
			/**
			 * 2：已下线
			 */
			public static final Integer OFFLINE = 2;
		}
	}
	
	/**
	 * 文章信息
	 */
	public static final class ARTICLE_INFO {
		/**
		 * 文章状态  0:草稿，1：待审核，2：审核通过，3：审核驳回，4:已发布，5：已下线
		 */
		public static final class STATE {
			/**
			 * 0:草稿
			 */
			public static final Integer DRAFT = 0;
			/**
			 * 1：待审核
			 */
			public static final Integer TO_BE_AUDITED = 1;
			/**
			 * 2：审核通过
			 */
			public static final Integer AUDIT_PASS = 2;
			/**
			 * 3：审核驳回
			 */
			public static final Integer AUDIT_REJECTED = 3;
			/**
			 * 4：已发布
			 */
			public static final Integer RELEASED = 4;
			/**
			 * 5：已下线
			 */
			public static final Integer OFFLINE = 5;
		}
	}
	
	/**
	 * 用户反馈
	 */
	public static final class USER_FEEDBACK {
		/**
		 * 处理状态  1:待处理，2：跟进中，3：已处理
		 */
		public static final class STATE {
			/**
			 * 1:待处理
			 */
			public static final Integer TO_DO = 1;
			/**
			 * 2：跟进中 
			 */
			public static final Integer PROCESSING = 2;
			/**
			 * 3：已处理
			 */
			public static final Integer DONE = 3;
		}
	}

}
